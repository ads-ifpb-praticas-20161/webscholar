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
    
    private WeekDay weekDay;
    private IntervalUnit intervalUnit;
    private Room room;

    public WeekDay getDay() {
        return weekDay;
    }
    
    public void setDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public IntervalUnit getInterval() {
        return intervalUnit;
    }

    public void setInterval(IntervalUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.weekDay);
        hash = 79 * hash + Objects.hashCode(this.intervalUnit);
        hash = 79 * hash + Objects.hashCode(this.room);
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
        if (!Objects.equals(this.weekDay, other.weekDay)) {
            return false;
        }
        if (!Objects.equals(this.intervalUnit, other.intervalUnit)) {
            return false;
        }
        if (!Objects.equals(this.room, other.room)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
