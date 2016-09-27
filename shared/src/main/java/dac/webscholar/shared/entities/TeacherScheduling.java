package dac.webscholar.shared.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vmvini on 11/09/16.
 */
@Entity @IdClass(TeacherSchedulingPK.class)
public class TeacherScheduling implements Serializable {

    @Id
    @Convert(converter = DayEnumTypeConverter.class)
    private DayEnum teacherWeekDay;

    @Id
    @ManyToOne
    private IntervalUnit teacherIntervalUnit;

    @Id
    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Discipline discipline;

    @ManyToOne
    private Course course;



    public TeacherScheduling(){}

    public TeacherScheduling(DayEnum teacherWeekDay, IntervalUnit teacherIntervalUnit, Teacher teacher) {
        this.teacherWeekDay = teacherWeekDay;
        this.teacherIntervalUnit = teacherIntervalUnit;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "TeacherScheduling{" +
                "teacherWeekDay=" + teacherWeekDay +
                ", teacherIntervalUnit=" + teacherIntervalUnit +
                ", teacher=" + teacher +
                '}';
    }

    public DayEnum getTeacherWeekDay() {
        return teacherWeekDay;
    }

    public void setTeacherWeekDay(DayEnum teacherWeekDay) {
        this.teacherWeekDay = teacherWeekDay;
    }

    public IntervalUnit getTeacherIntervalUnit() {
        return teacherIntervalUnit;
    }

    public void setTeacherIntervalUnit(IntervalUnit teacherIntervalUnit) {
        this.teacherIntervalUnit = teacherIntervalUnit;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
