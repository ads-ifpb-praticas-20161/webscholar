package dac.webscholar.sessionbeans.course;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.interfaces.CourseService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@CourseServiceQualifier
public class CourseServiceImpl implements CourseService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Course> listStrategyBuilder;

    private ListStrategy<Course> listStrategy;

    @Override
    public Course saveCourse(Course course) {
        return em.merge(course);
    }

    @Override
    public Course updateCourse(Course course) {
        return em.merge(course);
    }

    @Override
    public void removeCourse(Course course) {
        em.remove(em.find(Course.class, course.getId()));
    }

    @Override
    public List<Course> listAll() {
        listStrategy = listStrategyBuilder.createListStrategy().getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public List<Course> listNCourses(int initial, int end) {
        return null;
    }

    @Override
    public Course searchById(int id) {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .<Integer>addParameter("id", id)
                            .getListStrategy();

        return listStrategy.getSingleResult();
    }

    @Override
    public List<Course> searchByName(String name) {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("name", name)
                .getListStrategy();

        return listStrategy.getResultList();
    }
}
