package jsfbeans;

import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.sessionbeans.room.RoomServiceLocal;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@Named
@ViewScoped
public class RoomsMB implements Serializable {

    private Room newRoom;

    private Room selectedRoom;

    private List<Room> roomsList;

    private Room tempRoom;

    private RoomType labRoom, normalRoom;

    @Inject
    @RoomProxyQualifier
    private RoomServiceLocal roomService;

    @Inject
    private FacesMessagesFacade fmf;


    @PostConstruct
    private void init(){
        newRoom = new Room();
        labRoom = RoomType.LAB;
        normalRoom = RoomType.ROOM;

        roomsList = roomService.listAll();
    }

    public void saveRoom(){
        try {
            roomService.saveRoom(newRoom);
            fmf.successMsg("sucesso ao cadastrar sala", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public void updateRoom(){

        try{
            roomService.updateRoom(selectedRoom);
            fmf.successMsg("sucesso ao atualizar", null);
        }
        catch(ValidationException e){
            copyProps(tempRoom, selectedRoom);
            fmf.errorMsg(e.getMessage(), null);
        }

    }

    private void copyProps(Room source, Room target){
        target.setId(source.getId());
        target.setNome(source.getNome());
        target.setRoomType(source.getRoomType());

    }

    public void select(Room room){

        tempRoom = new Room();
        copyProps(room, tempRoom);
        System.out.println("SALA SELECIONADA: " + room);

        selectedRoom = room;
    }

    public void searchRoom(){
        try {
            roomsList = roomService.searchByTypeName(newRoom.getNome(), newRoom.getRoomType());

        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }


    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public List<Room> getRoomsList() {
        return roomsList;
    }

    public void setRoomsList(List<Room> roomsList) {
        this.roomsList = roomsList;
    }

    public Room getNewRoom() {
        return newRoom;
    }

    public void setNewRoom(Room newRoom) {
        this.newRoom = newRoom;
    }


    public RoomType getNormalRoom() {
        return normalRoom;
    }

    public void setNormalRoom(RoomType normalRoom) {
        this.normalRoom = normalRoom;
    }

    public RoomType getLabRoom() {
        return labRoom;
    }

    public void setLabRoom(RoomType labRoom) {
        this.labRoom = labRoom;
    }
}
