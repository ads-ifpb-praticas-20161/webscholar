package dac.webscholar.sessionbeans.course;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.CursoHorario;
import dac.webscholar.shared.entities.DayEnum;
import dac.webscholar.shared.entities.Intervalo;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;

/**
 * Created by marcusviniv on 23/09/2016.
 */
public interface CourseServiceLocal {

    Course saveCourse(Course course) throws ValidationException;

    Course updateCourse(Course course) throws ValidationException;

    void removeCourse(Course course) throws ValidationException;

    List<Course> listAll();

    List<Course> listNCourses(int initial, int end) throws ValidationException;

    Course searchById(int id) throws ValidationException;

    List<Course> searchByName(String name) throws ValidationException;

    Map<Intervalo, Map<DayEnum, CursoHorario>>  mapHorario();

}
