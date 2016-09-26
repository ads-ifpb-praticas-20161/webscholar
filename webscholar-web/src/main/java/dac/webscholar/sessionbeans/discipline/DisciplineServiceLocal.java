package dac.webscholar.sessionbeans.discipline;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.Discipline;
import dac.webscholar.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */
public interface DisciplineServiceLocal {

    List<Discipline> findByName(String name) throws ValidationException;

    Discipline findByNameCourse(String name, Course course) throws ValidationException;

    Discipline save(Discipline discipline) throws ValidationException;

    Discipline update(Discipline discipline) throws ValidationException;

    void remove(Discipline discipline) throws ValidationException;

    List<Discipline> listAll();

}
