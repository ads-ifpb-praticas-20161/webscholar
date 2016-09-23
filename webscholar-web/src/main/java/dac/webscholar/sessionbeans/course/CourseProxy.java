package dac.webscholar.sessionbeans.course;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.CourseService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@Stateless
@Remote(CourseService.class)
@Local(CourseServiceLocal.class)
@CourseProxyQualifier
public class CourseProxy implements Serializable, CourseServiceLocal, CourseService {

    @Inject
    @CourseServiceQualifier
    private CourseService courseService;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Course saveCourse(Course course) throws ValidationException {

        if(course == null){
            throw new ValidationException("Curso está nulo");
        }

        if(course.getName() == null || course.getName().trim().isEmpty()){
            throw new ValidationException("Nome do curso está vazio");
        }
        if(course.getSeasons() < 1 ){
            throw new ValidationException("Curso deve ter pelo menos um periodo");
        }
        try{
            return courseService.saveCourse(course);
        }
        catch(ValidationException e){
            if(e.getExceptionType().equals(ExceptionTypes.DATABASE)){
                throw new ValidationException("Já existe curso com esse nome", e.getCause(), e.getExceptionType());
            }
            return null;
        }

    }

    @Override
    public Course updateCourse(Course course) throws ValidationException{

        if(course == null){
            throw new ValidationException("Curso está nulo");
        }

        if(course.getName() == null || course.getName().trim().isEmpty()){
            throw new ValidationException("Nome do curso está vazio");
        }
        if(course.getSeasons() < 1 ){
            throw new ValidationException("Curso deve ter pelo menos um periodo");
        }
        try {
            return courseService.updateCourse(course);
        }
        catch(ValidationException e){
            if(e.getExceptionType().equals(ExceptionTypes.DATABASE)){
                throw new ValidationException("Já existe curso com esse nome", e.getCause(), e.getExceptionType());
            }
            return null;
        }
    }

    @Override
    public void removeCourse(Course course) throws ValidationException {
        if(course == null){
            throw new ValidationException("Curso está nulo");
        }
        if(course.getId() < 1){
            throw new ValidationException("Id de curso inválida");
        }
        courseService.removeCourse(course);

    }

    @Override
    public List<Course> listAll()  {
        return courseService.listAll();
    }

    @Override
    public List<Course> listNCourses(int initial, int end) throws ValidationException {
        return courseService.listNCourses(initial, end);
    }

    @Override
    public Course searchById(int id) throws ValidationException {

        if(id <= 0){
            throw new ValidationException("id deve ser numero positivo");
        }

        return courseService.searchById(id);
    }

    @Override
    public List<Course> searchByName(String name) throws ValidationException {
        if(name == null || name.trim().isEmpty()){
            throw new ValidationException("Digite o nome do curso");
        }
        return courseService.searchByName(name);
    }
}
