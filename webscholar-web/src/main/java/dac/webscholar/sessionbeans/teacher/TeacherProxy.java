package dac.webscholar.sessionbeans.teacher;

import dac.webscholar.Utils.PatternValidator;
import dac.webscholar.Utils.ValidatorType;
import dac.webscholar.cdiqualifiers.PatternValidatorQualifier;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.TeacherService;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@Stateless
@Remote(TeacherService.class)
@Local(TeacherServiceLocal.class)
@TeacherProxyQualifier
public class TeacherProxy implements Serializable, TeacherService, TeacherServiceLocal {

    @Inject
    @TeacherServiceQualifier
    private TeacherService teacherService;

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.EMAIL)
    private PatternValidator emailValidator;

    @Inject
    @PatternValidatorQualifier(type = ValidatorType.CPF)
    private PatternValidator cpfValidator;

    @Inject
    @JMSConnectionFactory("jms/QueueConnectionFactory")
    private JMSContext context;

    @Resource(name = "jms/teacherQueue")
    private Destination newTeacherQueue;

    @Resource(name = "jms/emailQueue")
    private Destination emailQueue;



    private void validate(Teacher t) throws ValidationException {

        if(t == null){
            throw new ValidationException("Professor não foi selecionado!");
        }

        if (t.getCpf() == null || t.getCpf().trim().isEmpty()) {
            throw new ValidationException("Cpf está vazio!");
        }
        if (!cpfValidator.isValid(t.getCpf())) {
            throw new ValidationException("Cpf inválido!");
        }
        if (t.getUserType() == null) {
            throw new ValidationException("Tipo do usuário não está definido");
        }
        if (t.getName() == null || t.getName().trim().isEmpty()) {
            throw new ValidationException("Nome está vazio!");
        }
        if (t.getEmail() == null || t.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email está vazio!");
        }
        if (!emailValidator.isValid(t.getEmail())) {
            throw new ValidationException("Email inválido!");
        }
        if (t.getPassword() == null || t.getPassword().trim().isEmpty()) {
            throw new ValidationException("Senha está vazia!");
        }

    }

    @Override
    public void saveTeacher(Teacher teacher) throws ValidationException {
        validate(teacher);
        teacher.setActivated(false);
        try {
            teacherService.saveTeacher(teacher);
            TextMessage m = context.createTextMessage("Um novo usuário deseja cadastrar-se como professor");
            JMSProducer producer = context.createProducer();
            producer.send(newTeacherQueue, m);
        } catch (ValidationException e) {
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Já existe professor com esses dados", e.getCause(), e.getExceptionType());
            }
        }

    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws ValidationException {
        validate(teacher);
        try {
            return teacherService.updateTeacher(teacher);
        } catch (ValidationException e) {
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Já existe professor com esses dados", e.getCause(), e.getExceptionType());
            }
            return null;
        }
    }

    @Override
    public void removeTeacher(Teacher teacher) throws ValidationException {
        if (teacher.getId() < 1) {
            throw new ValidationException("Esse professor não existe");
        }
        teacherService.removeTeacher(teacher);
    }

    @Override
    public List<Teacher> listAll() {
        return teacherService.listAll();
    }

    @Override
    public Teacher searchByCpf(String cpf) throws ValidationException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new ValidationException("cpf está vazio!");
        }
        return teacherService.searchByCpf(cpf);
    }

    @Override
    public List<Teacher> searchByName(String name) throws ValidationException {
        if (name == null || name.trim().isEmpty()) {
            throw new ValidationException("nome está vazio");
        }
        return teacherService.searchByName(name);
    }

    @Override
    public List<Teacher> getInactiveTeachers() {

        return teacherService.getInactiveTeachers();

    }

    @Override
    public void activateTeacher(Teacher teacher) throws ValidationException{
        validate(teacher);
        try {
            ObjectMessage om = context.createObjectMessage();
            om.setObject(teacher);
            JMSProducer producer = context.createProducer();
            producer.send(emailQueue, om);
            System.out.println("enviou comando para ativar");
        }
        catch(JMSException e){
            e.printStackTrace();
        }
    }
}
