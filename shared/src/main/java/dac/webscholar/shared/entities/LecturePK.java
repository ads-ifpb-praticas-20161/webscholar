/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author vmvini
 */


public class LecturePK implements Serializable {
    
    
    private RoomSchedulingPK roomScheduling;
    
    
    private int teacher;

    public RoomSchedulingPK getRoomScheduling() {
        return roomScheduling;
    }

    public void setRoomScheduling(RoomSchedulingPK roomScheduling) {
        this.roomScheduling = roomScheduling;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.roomScheduling);
        hash = 79 * hash + this.teacher;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LecturePK other = (LecturePK) obj;
        if (!Objects.equals(this.roomScheduling, other.roomScheduling)) {
            return false;
        }
        if (this.teacher != other.teacher) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
