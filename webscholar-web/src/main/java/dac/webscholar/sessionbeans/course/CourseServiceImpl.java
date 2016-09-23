package dac.webscholar.sessionbeans.course;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.CourseService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
            throw new ValidationException(ex.getMessage());
        }
        return o;
    }

    @Override
    public Course saveCourse(Course course)throws ValidationException {

        return em.merge(course);

    }

    @Override
    public Course updateCourse(Course course) throws ValidationException {
        return em.merge(course);

    }

    @Override
    public void removeCourse(Course course)throws ValidationException {
        em.remove(em.find(Course.class, course.getId()));
    }

    @Override
    public List<Course> listAll() {
        listStrategy = listStrategyBuilder.createListStrategy().getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public List<Course> listNCourses(int initial, int end) throws ValidationException {
        return null;
    }

    @Override
    public Course searchById(int id) throws ValidationException {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .<Integer>addParameter("id", id)
                            .getListStrategy();

        return listStrategy.getSingleResult();
    }

    @Override
    public List<Course> searchByName(String name) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("name", name)
                .getListStrategy();

        return listStrategy.getResultList();
    }
}
