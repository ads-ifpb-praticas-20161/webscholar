package dac.webscholar.sessionbeans.room;

import dac.webscholar.cdiqualifiers.RoomServiceQualifier;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.exceptions.ExceptionTypes;
import dac.webscholar.shared.exceptions.ValidationException;
import dac.webscholar.shared.interfaces.RoomService;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */
@RoomServiceQualifier
public class RoomServiceImpl implements Serializable, RoomService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Room> listStrategyBuilder;

    private ListStrategy<Room> listStrategy;

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
    public Room saveRoom(Room room) throws ValidationException  {
        return em.merge(room);
    }

    @Override
    public Room updateRoom(Room room) throws ValidationException {
        return em.merge(room);
    }

    @Override
    public void removeRoom(Room room) throws ValidationException {
        em.remove(em.find(Room.class, room.getId()));
    }

    @Override
    public List<Room> searchByName(String name) throws ValidationException {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .<String>addParameter("nome", name)
                            .getListStrategy();
        return listStrategy.getResultList();


    }

    @Override
    public Room searchById(int id) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<Integer>addParameter("id", id)
                .getListStrategy();
        return listStrategy.getSingleResult();
    }

    @Override
    public List<Room> searchByType(RoomType roomType) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<RoomType>addParameter("roomType", roomType)
                .getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public List<Room> searchByTypeName(String name, RoomType roomType) throws ValidationException {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<RoomType>addParameter("roomType", roomType)
                .<String>addParameter("nome", name)
                .getListStrategy();
        return listStrategy.getResultList();
    }
}
