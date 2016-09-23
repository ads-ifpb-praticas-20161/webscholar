package dac.webscholar.sessionbeans.course;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.interfaces.CourseService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

    @Override
    public Course saveCourse(Course course) {

        if(course == null){
            throw new IllegalArgumentException("Curso está nulo");
        }

        if(course.getName() == null || course.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Nome do curso está vazio");
        }
        if(course.getSeasons() < 1 ){
            throw new IllegalArgumentException("Curso deve ter pelo menos um periodo");
        }

        return courseService.saveCourse(course);

    }

    @Override
    public Course updateCourse(Course course) {

        if(course == null){
            throw new IllegalArgumentException("Curso está nulo");
        }

        if(course.getName() == null || course.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Nome do curso está vazio");
        }
        if(course.getSeasons() < 1 ){
            throw new IllegalArgumentException("Curso deve ter pelo menos um periodo");
        }

        return courseService.updateCourse(course);

    }

    @Override
    public void removeCourse(Course course) {
        if(course == null){
            throw new IllegalArgumentException("Curso está nulo");
        }

        courseService.removeCourse(course);
    }

    @Override
    public List<Course> listAll() {
        return courseService.listAll();
    }

    @Override
    public List<Course> listNCourses(int initial, int end) {
        return courseService.listNCourses(initial, end);
    }

    @Override
    public Course searchById(int id) {

        if(id <= 0){
            throw new IllegalArgumentException("id deve ser numero positivo");
        }

        return courseService.searchById(id);
    }

    @Override
    public List<Course> searchByName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Digite o nome do curso");
        }
        return courseService.searchByName(name);
    }
}
