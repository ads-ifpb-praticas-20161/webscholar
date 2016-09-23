package dac.webscholar.sessionbeans;

import dac.webscholar.cdiqualifiers.RoomServiceQualifier;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.interfaces.RoomService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */
@RoomServiceQualifier
public class RoomServiceImpl implements RoomService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ListStrategyBuilder<Room> listStrategyBuilder;

    private ListStrategy<Room> listStrategy;


    @Override
    public Room saveRoom(Room room) {
        return em.merge(room);
    }

    @Override
    public Room updateRoom(Room room) {
        return em.merge(room);
    }

    @Override
    public void removeRoom(Room room) {
        em.remove(em.find(Room.class, room));
    }

    @Override
    public List<Room> searchByName(String name) {
        listStrategy = listStrategyBuilder
                            .createListStrategy()
                            .<String>addParameter("nome", name)
                            .getListStrategy();
        return listStrategy.getResultList();


    }

    @Override
    public Room searchById(int id) {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<Integer>addParameter("id", id)
                .getListStrategy();
        return listStrategy.getSingleResult();
    }

    @Override
    public List<Room> searchByType(RoomType roomType) {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<RoomType>addParameter("roomType", roomType)
                .getListStrategy();
        return listStrategy.getResultList();
    }

    @Override
    public List<Room> searchByTypeName(String name, RoomType roomType) {
        listStrategy = listStrategyBuilder
                .createListStrategy()
                .<RoomType>addParameter("roomType", roomType)
                .<String>addParameter("nome", name)
                .getListStrategy();
        return listStrategy.getResultList();
    }
}
