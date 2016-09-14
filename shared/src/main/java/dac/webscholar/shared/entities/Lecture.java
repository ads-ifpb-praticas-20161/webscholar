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

@Entity @IdClass(value=LecturePK.class)
public class Lecture implements Serializable {
    
    @Id
    @OneToOne
    private RoomScheduling roomScheduling;
    
    @Id
    @OneToOne
    private TeacherScheduling teacherScheduling;


    @ManyToOne
    private Discipline discipline;

    public Lecture(){

    }

    public Lecture(RoomScheduling roomScheduling, TeacherScheduling teacherScheduling, Discipline discipline) {
        this.roomScheduling = roomScheduling;
        this.teacherScheduling = teacherScheduling;
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "roomScheduling=" + roomScheduling +
                ", teacherScheduling=" + teacherScheduling +
                ", discipline=" + discipline +
                '}';
    }

    public RoomScheduling getRoomScheduling() {
        return roomScheduling;
    }

    public void setRoomScheduling(RoomScheduling roomScheduling) {
        this.roomScheduling = roomScheduling;
    }

    public TeacherScheduling getTeacherScheduling() {
        return teacherScheduling;
    }

    public void setTeacherScheduling(TeacherScheduling teacherScheduling) {
        this.teacherScheduling = teacherScheduling;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
