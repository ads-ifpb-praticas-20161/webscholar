package dac.webscholar.sessionbeans.room;

import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.exceptions.ValidationException;

import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */
public interface RoomServiceLocal {

    Room saveRoom(Room room) throws ValidationException;

    Room updateRoom(Room room) throws ValidationException;

    void removeRoom(Room room) throws ValidationException;

    List<Room> searchByName(String name) throws ValidationException;

    Room searchById(int id) throws ValidationException;

    List<Room> searchByType(RoomType roomType) throws ValidationException;

    List<Room> searchByTypeName(String name, RoomType roomType) throws ValidationException;

    List<Room> listAll();

}
