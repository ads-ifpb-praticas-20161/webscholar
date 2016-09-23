package dac.webscholar.shared.interfaces;

import dac.webscholar.shared.entities.Course;

import java.util.List;

/**
 * Created by marcusviniv on 23/09/2016.
 */
public interface CourseService {

    Course saveCourse(Course course);

    Course updateCourse(Course course);

    void removeCourse(Course course);

    List<Course> listAll();

    List<Course> listNCourses(int initial, int end);

    Course searchById(int id);

    List<Course> searchByName(String name);



}
