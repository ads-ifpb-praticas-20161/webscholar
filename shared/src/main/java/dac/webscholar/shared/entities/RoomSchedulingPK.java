/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;


/**
 *
 * @author vmvini
 */
public class RoomSchedulingPK implements Serializable{

    private int room;

    private String roomIntervalUnit;

    private DayEnum roomWeekDay;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomSchedulingPK that = (RoomSchedulingPK) o;

        if (room != that.room) return false;
        if (!roomIntervalUnit.equals(that.roomIntervalUnit)) return false;
        return roomWeekDay == that.roomWeekDay;

    }

    @Override
    public int hashCode() {
        int result = room;
        result = 31 * result + roomIntervalUnit.hashCode();
        result = 31 * result + roomWeekDay.hashCode();
        return result;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getRoomIntervalUnit() {
        return roomIntervalUnit;
    }

    public void setRoomIntervalUnit(String roomIntervalUnit) {
        this.roomIntervalUnit = roomIntervalUnit;
    }

    public DayEnum getRoomWeekDay() {
        return roomWeekDay;
    }

    public void setRoomWeekDay(DayEnum roomWeekDay) {
        this.roomWeekDay = roomWeekDay;
    }
}
