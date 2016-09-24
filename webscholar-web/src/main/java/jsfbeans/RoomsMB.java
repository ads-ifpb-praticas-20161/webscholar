package jsfbeans;

import dac.webscholar.cdiqualifiers.RoomProxyQualifier;
import dac.webscholar.sessionbeans.room.RoomServiceLocal;
import dac.webscholar.shared.entities.Room;
import dac.webscholar.shared.entities.RoomType;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Produces;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@Named
@RequestScoped
public class RoomsMB {

    private Room newRoom;

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
