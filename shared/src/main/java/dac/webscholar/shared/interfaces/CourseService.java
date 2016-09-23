package dac.webscholar.shared.interfaces;

import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Created by marcusviniv on 23/09/2016.
 */
public interface CourseService {

    Course saveCourse(Course course) throws ValidationException;

    Course updateCourse(Course course) throws ValidationException;

    void removeCourse(Course course) throws ValidationException;

    List<Course> listAll();

    List<Course> listNCourses(int initial, int end) throws ValidationException;

    Course searchById(int id) throws ValidationException;

    List<Course> searchByName(String name) throws ValidationException;



}
