package dac.webscholar.sessionbeans.teacher;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.TeacherService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.jms.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */

@TeacherServiceQualifier
public class TeacherServiceImpl implements TeacherService{

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Teacher> listStrategyBuilder;

    private ListStrategy<Teacher> listStrategy;

    @Inject
    @JMSConnectionFactory("jms/QueueConnectionFactory")
    private JMSContext context;

    @Resource(name = "jms/teacherQueue")
    private Destination destination;


    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object interceptor(InvocationContext ic) throws Exception {
        Object o = null;
        try {
            o = ic.proceed();
            if (!sessionContext.getRollbackOnly()) {
                em.flush();
            }
        } catch (PersistenceException ex) {
            throw new ValidationException(ex.getMessage(), ex, ExceptionTypes.DATABASE);
        }
        return o;
    }


    @Override
    public Teacher saveTeacher(Teacher teacher) throws ValidationException{

        TextMessage m = context.createTextMessage("QUERO SER PROFESSOR!");
        JMSProducer producer = context.createProducer();
        producer.send(destination, m);
        System.out.println("enviou mensagem jms");

        return em.merge(teacher);

    }

    @Override
    public Teacher updateTeacher(Teacher teacher) throws ValidationException {
        return em.merge(teacher);
    }

    @Override
    public void removeTeacher(Teacher teacher) throws ValidationException {
        em.remove(em.find(Teacher.class, teacher.getId()));
    }

    @Override
    public List<Teacher> listAll() throws ValidationException {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public Teacher searchByCpf(String cpf) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("cpf", cpf)
                .getListStrategy();
        return listStrategy.getSingleResult();
    }

    @Override
    public List<Teacher> searchByName(String name) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("name", name)
                .getListStrategy();
        return listStrategy.getResultList();
    }
}
