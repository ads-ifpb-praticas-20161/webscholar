package dac.webscholar.sessionbeans;

import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;

import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */
public interface RoomServiceLocal {

    Room saveRoom(Room room);

    Room updateRoom(Room room);

    void removeRoom(Room room);

    List<Room> searchByName(String name);

    Room searchById(int id);

    List<Room> searchByType(RoomType roomType);

    List<Room> searchByTypeName(String name, RoomType roomType);

}
