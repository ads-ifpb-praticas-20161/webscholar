package dac.webscholar.sessionbeans.discipline;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.Discipline;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.DisciplineService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */
@Stateless
@Remote(DisciplineService.class)
@Local(DisciplineServiceLocal.class)
@DisciplineProxyQualifier
public class DisciplineStatelessProxy implements Serializable, DisciplineServiceLocal, DisciplineService {

    @Inject
    @DisciplineServiceQualifier
    private DisciplineService disciplineService;


    private void validate(Discipline dis) throws ValidationException{
        if(dis == null){
            throw new ValidationException("Disciplina está nula!");
        }
        if(dis.getName() == null || dis.getName().trim().isEmpty()){
            throw new ValidationException("Nome da disciplina está vazio!");
        }
        if(dis.getCourse() == null){
            throw new ValidationException("Curso está nulo!");
        }
        if(dis.getSeason() < 1){
            throw new ValidationException("Discipline precisa ter periodo acima de 0");
        }

    }

    @Override
    public List<Discipline> findByName(String name) throws ValidationException {
        if(name == null || name.trim().isEmpty()){
            return disciplineService.listAll();
        }
        else{
            return disciplineService.findByName(name);
        }
    }

    @Override
    public Discipline findByNameCourse(String name, Course course) throws ValidationException {
        if(name == null || name.trim().isEmpty()){
            throw new ValidationException("Nome está nulo!");
        }
        if(course == null){
            throw new ValidationException("Curso está nulo");
        }
        return disciplineService.findByNameCourse(name, course);
    }

    @Override
    public Discipline save(Discipline discipline) throws ValidationException {
        validate(discipline);
        try {
            return disciplineService.save(discipline);
        }
        catch(ValidationException e){
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Disciplina ja existente",  e.getCause(), e.getExceptionType());
            }
            return null;
        }

    }

    @Override
    public Discipline update(Discipline discipline) throws ValidationException {
        validate(discipline);
        try {
            return disciplineService.update(discipline);
        }
        catch(ValidationException e){
            if (e.getExceptionType().equals(ExceptionTypes.DATABASE)) {
                throw new ValidationException("Disciplina ja existente",  e.getCause(), e.getExceptionType());
            }
            return null;
        }
    }

    @Override
    public void remove(Discipline discipline) throws ValidationException {
        validate(discipline);
        disciplineService.remove(discipline);
    }

    @Override
    public List<Discipline> listAll() {
        return disciplineService.listAll();
    }
}
