/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.Embeddable;

/**
 *
 * @author vmvini
 */

@Embeddable
public class LecturePK {
    
    private Day day;
    private Interval interval;
    private Room room;

    public Day getDay() {
        return day;
    }
    
    public void setDay(Day day) {
        this.day = day;
    }

    public Interval getInterval() {
        return interval;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    
    
    
    
}
