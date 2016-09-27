package dac.webscholar.sessionbeans.discipline;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.Discipline;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.DisciplineService;

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
 * Created by marcusviniv on 25/09/2016.
 */

@DisciplineServiceQualifier
public class DisciplineServiceImpl implements DisciplineService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Discipline> listStrategyBuilder;

    private ListStrategy<Discipline> listStrategy;

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
    public List<Discipline> findByName(String name) throws ValidationException {
        listStrategy = listStrategyBuilder
                        .createListStrategy()
                        .<String>addParameter("name", name)
                        .getListStrategy();

        return listStrategy.getResultList();
    }

    @Override
    public Discipline findByNameCourse(String name, Course course) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<String>addParameter("name", name)
                .<Course>addParameter("course", course)
                .getListStrategy();

        return listStrategy.getSingleResult();
    }

    @Override
    public Discipline save(Discipline discipline) throws ValidationException {
        return em.merge(discipline);
    }

    @Override
    public Discipline update(Discipline discipline) throws ValidationException {
        return em.merge(discipline);
    }

    @Override
    public void remove(Discipline discipline) throws ValidationException {
        em.remove(em.find(Discipline.class, discipline.getId() ));
    }

    @Override
    public List<Discipline> listAll() {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .getListStrategy();

        return listStrategy.getResultList();
    }
}
