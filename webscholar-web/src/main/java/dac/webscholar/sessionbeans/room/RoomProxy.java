package dac.webscholar.sessionbeans.room;

import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.cdiqualifiers.RoomServiceQualifier;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.interfaces.RoomService;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 22/09/2016.
 */

@Stateless
@RoomProxyQualifier
@Remote(RoomService.class)
@Local(RoomServiceLocal.class)
public class RoomProxy implements Serializable, RoomService, RoomServiceLocal{

    @Inject
    @RoomServiceQualifier
    private RoomService roomService;

    private void validate(Room room){
        if(room == null){
            throw new IllegalArgumentException("Sala nula");
        }
        if(room.getNome() == null || room.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Nome da sala nulo ou vazio");
        }
        if(room.getRoomType() == null){
            throw new IllegalArgumentException("Tipo da sala nulo");
        }
    }

    @Override
    public Room saveRoom(Room room) {
        validate(room);
        return roomService.saveRoom(room);
    }

    @Override
    public Room updateRoom(Room room) {
        validate(room);
        return roomService.updateRoom(room);
    }

    @Override
    public void removeRoom(Room room) {
        validate(room);
        roomService.removeRoom(room);
    }

    @Override
    public List<Room> searchByName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nome da sala está vazio");
        }
        return roomService.searchByName(name);
    }

    @Override
    public Room searchById(int id) {
        if(id < 1){
            throw new IllegalArgumentException("Id não pode ser menor que 1");
        }
        try {
            return roomService.searchById(id);
        }
        catch (RuntimeException e){
            return null;
        }
    }

    @Override
    public List<Room> searchByType(RoomType roomType) {
        if(roomType == null){
            throw new IllegalArgumentException("Tipo de sala nulo");
        }
        return roomService.searchByType(roomType);
    }

    @Override
    public List<Room> searchByTypeName(String name, RoomType roomType) {
        if(roomType == null){
            throw new IllegalArgumentException("Tipo de sala nulo");
        }
        if(name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nome da sala está vazio");
        }
        return roomService.searchByTypeName(name, roomType);
    }
}
