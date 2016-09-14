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

    private TeacherSchedulingPK teacherScheduling;



    public LecturePK(){

    }

    public LecturePK(RoomSchedulingPK roomScheduling, TeacherSchedulingPK teacherScheduling) {
        this.roomScheduling = roomScheduling;
        this.teacherScheduling = teacherScheduling;
    }

    public RoomSchedulingPK getRoomScheduling() {
        return roomScheduling;
    }

    public void setRoomScheduling(RoomSchedulingPK roomScheduling) {
        this.roomScheduling = roomScheduling;
    }

    public TeacherSchedulingPK getTeacherScheduling() {
        return teacherScheduling;
    }

    public void setTeacherScheduling(TeacherSchedulingPK teacherScheduling) {
        this.teacherScheduling = teacherScheduling;
    }

    @Override
    public String toString() {
        return "LecturePK{" +
                "roomScheduling=" + roomScheduling +
                ", teacherScheduling=" + teacherScheduling +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LecturePK lecturePK = (LecturePK) o;

        if (!roomScheduling.equals(lecturePK.roomScheduling)) return false;
        return teacherScheduling.equals(lecturePK.teacherScheduling);

    }

    @Override
    public int hashCode() {
        int result = roomScheduling.hashCode();
        result = 31 * result + teacherScheduling.hashCode();
        return result;
    }
}
