package dac.webscholar.sessionbeans.lecture;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Discipline;
import dac.webscholar.shared.entities.Lecture;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.LectureService;

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
 * Created by marcusviniv on 26/09/2016.
 */

@LectureServiceQualifier
public class LectureServiceImpl implements LectureService {


    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Lecture> listStrategyBuilder;

    private ListStrategy<Lecture> listStrategy;

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
    public Lecture save(Lecture lecture) throws ValidationException {
        em.persist(lecture);
        return em.merge(lecture);
    }

    @Override
    public Lecture update(Lecture lecture) throws ValidationException {
        return em.merge(lecture);
    }

    @Override
    public void remove(Lecture lecture)  throws ValidationException{
        em.remove(lecture);
    }

    @Override
    public List<Lecture> listAll() {
        listStrategy = listStrategyBuilder
                        .createListStrategy()
                        .getListStrategy();
        return listStrategy.getResultList();
    }
}
