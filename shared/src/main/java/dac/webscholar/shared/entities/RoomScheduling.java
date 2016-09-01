/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 *
 * @author vmvini
 */

@Entity @IdClass(value = RoomSchedulingPK.class)
public class RoomScheduling implements Serializable {
    
    @Id
    @ManyToOne
    private WeekDay weekDay;
    
    @Id
    @ManyToOne
    private IntervalUnit intervalUnit;
    
    @Id
    @ManyToOne
    private Room room;

    public RoomScheduling(WeekDay weekDay, IntervalUnit intervalUnit, Room room) {
        this.weekDay = weekDay;
        this.intervalUnit = intervalUnit;
        this.room = room;
    }
    
    public RoomScheduling(){
        
    }
    

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public IntervalUnit getIntervalUnit() {
        return intervalUnit;
    }

    public void setIntervalUnit(IntervalUnit intervalUnit) {
        this.intervalUnit = intervalUnit;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    
}
