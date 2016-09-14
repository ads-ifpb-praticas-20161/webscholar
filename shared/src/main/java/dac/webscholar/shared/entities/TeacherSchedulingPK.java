package dac.webscholar.shared.entities;


import java.io.Serializable;

/**
 * Created by vmvini on 11/09/16.
 */


public class TeacherSchedulingPK implements Serializable{

    private DayEnum teacherWeekDay;

    private String teacherIntervalUnit;


    private int teacher;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherSchedulingPK that = (TeacherSchedulingPK) o;

        if (teacher != that.teacher) return false;
        if (teacherWeekDay != that.teacherWeekDay) return false;
        return teacherIntervalUnit.equals(that.teacherIntervalUnit);

    }

    @Override
    public int hashCode() {
        int result = teacherWeekDay.hashCode();
        result = 31 * result + teacherIntervalUnit.hashCode();
        result = 31 * result + teacher;
        return result;
    }


    public DayEnum getTeacherWeekDay() {
        return teacherWeekDay;
    }

    public void setTeacherWeekDay(DayEnum teacherWeekDay) {
        this.teacherWeekDay = teacherWeekDay;
    }

    public String getTeacherIntervalUnit() {
        return teacherIntervalUnit;
    }

    public void setTeacherIntervalUnit(String teacherIntervalUnit) {
        this.teacherIntervalUnit = teacherIntervalUnit;
    }

    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(int teacher) {
        this.teacher = teacher;
    }
}
