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
public class RoomSchedulingPK implements Serializable{

    private DayEnum weekDay;
    
    private IntervalUnitPK intervalUnit;
    
    private int room;

    public DayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayEnum weekDay) {
        this.weekDay = weekDay;
    }

    public IntervalUnitPK getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(IntervalUnitPK intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.weekDay);
        hash = 37 * hash + Objects.hashCode(this.intervalUnit);
        hash = 37 * hash + this.room;
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
        final RoomSchedulingPK other = (RoomSchedulingPK) obj;
        if (this.weekDay != other.weekDay) {
            return false;
        }
        if (!Objects.equals(this.intervalUnit, other.intervalUnit)) {
            return false;
        }
        if (this.room != other.room) {
            return false;
        }
        return true;
    }

    
    
    
}
