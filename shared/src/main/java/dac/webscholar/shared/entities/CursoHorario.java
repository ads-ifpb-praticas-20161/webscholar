package dac.webscholar.shared.entities;

import java.util.Date;

/**
 * Created by marcusviniv on 26/09/2016.
 */
public class CursoHorario {

    private String course;
    private String teacher;
    private String discipline;
    private String intervalo;
    private DayEnum dia;
    private Date initialDate;
    private Date endDate;


    public CursoHorario(String course, String teacher, String discipline, String intervalo, String dia, Date initialDate, Date endDate) {
        System.out.println("GERANDO CURSO HORARIO" + dia);
        this.course = course;
        this.teacher = teacher;
        this.discipline = discipline;
        this.intervalo = intervalo;
        this.dia = DayEnum.getDayEnum(dia);
        this.initialDate = initialDate;
        this.endDate = endDate;
        System.out.println("GEROU CURSO HORARIO");
    }

    public CursoHorario(){

    }


    @Override
    public String toString() {
        return "CursoHorario{" +
                "course='" + course + '\'' +
                ", teacher='" + teacher + '\'' +
                ", discipline='" + discipline + '\'' +
                ", intervalo='" + intervalo + '\'' +
                ", dia=" + dia +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                '}';
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getintervalo() {
        return intervalo;
    }

    public void setintervalo(String intervalo) {
        this.intervalo = intervalo;
    }

    public DayEnum getDay() {
        return dia;
    }

    public void setDay(DayEnum dia) {
        System.out.println("SET DAY CHAMADO");
        this.dia = dia;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
