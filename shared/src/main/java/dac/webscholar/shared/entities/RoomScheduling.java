/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author vmvini
 */

@Entity @IdClass(value = RoomSchedulingPK.class)
public class RoomScheduling implements Serializable {

    @Id
    @ManyToOne
    private Room room;

    @Id
    @ManyToOne
    private IntervalUnit roomIntervalUnit;

    @Id
    @Convert(converter = DayEnumTypeConverter.class)
    private DayEnum roomWeekDay;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Discipline discipline;

    @ManyToOne
    private Teacher teacher;

    public RoomScheduling(){}

    public RoomScheduling(Room room, IntervalUnit roomIntervalUnit, DayEnum roomWeekDay) {
        this.room = room;
        this.roomIntervalUnit = roomIntervalUnit;
        this.roomWeekDay = roomWeekDay;
    }

    @Override
    public String toString() {
        return "RoomScheduling{" +
                "room=" + room +
                ", roomIntervalUnit='" + roomIntervalUnit + '\'' +
                ", roomWeekDay=" + roomWeekDay +
                '}';
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public IntervalUnit getRoomIntervalUnit() {
        return roomIntervalUnit;
    }

    public void setRoomIntervalUnit(IntervalUnit roomIntervalUnit) {
        this.roomIntervalUnit = roomIntervalUnit;
    }

    public DayEnum getRoomWeekDay() {
        return roomWeekDay;
    }

    public void setRoomWeekDay(DayEnum roomWeekDay) {
        this.roomWeekDay = roomWeekDay;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
