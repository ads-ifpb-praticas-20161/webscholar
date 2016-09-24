package dac.webscholar.sessionbeans.room;

import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.cdiqualifiers.RoomServiceQualifier;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.exceptions.ValidationException;
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

    private void validate(Room room) throws ValidationException{
        if(room == null){
            throw new ValidationException("Sala nula");
        }
        if(room.getNome() == null || room.getNome().trim().isEmpty()){
            throw new ValidationException("Nome da sala nulo ou vazio");
        }
        if(room.getRoomType() == null){
            throw new ValidationException("Tipo da sala nulo");
        }
    }

    @Override
    public Room saveRoom(Room room) throws ValidationException {
        validate(room);
        try {
            return roomService.saveRoom(room);
        }
        catch(ValidationException e){
            throw new ValidationException("Já existe sala com esse nome");
        }
    }

    @Override
    public Room updateRoom(Room room) throws ValidationException{
        validate(room);
        try {
            return roomService.updateRoom(room);
        }
        catch(ValidationException e){
            throw new ValidationException("Já existe sala com esse nome");
        }
    }

    @Override
    public void removeRoom(Room room) throws ValidationException {
        validate(room);

        if(room.getId() < 1){
            throw new ValidationException("Sala não existe!");
        }

        roomService.removeRoom(room);
    }

    @Override
    public List<Room> searchByName(String name) throws ValidationException {
        if(name == null || name.trim().isEmpty()){
            throw new ValidationException("Nome da sala está vazio");
        }
        return roomService.searchByName(name);
    }

    @Override
    public Room searchById(int id) throws ValidationException{
        if(id < 1){
            throw new ValidationException("Id não pode ser menor que 1");
        }
        return roomService.searchById(id);

    }

    @Override
    public List<Room> searchByType(RoomType roomType) throws ValidationException {
        if(roomType == null){
            throw new ValidationException("Tipo de sala nulo");
        }
        return roomService.searchByType(roomType);
    }

    @Override
    public List<Room> searchByTypeName(String name, RoomType roomType) throws ValidationException {
        if(roomType == null){
            throw new ValidationException("Tipo de sala nulo");
        }
        if(name == null || name.trim().isEmpty()){
            throw new ValidationException("Nome da sala está vazio");
        }
        return roomService.searchByTypeName(name, roomType);
    }
}
