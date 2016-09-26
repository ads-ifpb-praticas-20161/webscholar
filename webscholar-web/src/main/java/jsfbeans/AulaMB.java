package jsfbeans;

import dac.webscholar.sessionbeans.IntervalUnitServiceLocal;
import dac.webscholar.sessionbeans.lecture.LectureProxyQualifier;
import dac.webscholar.sessionbeans.lecture.LectureServiceLocal;
import dac.webscholar.shared.entities.*;
import dac.webscholar.shared.exceptions.ValidationException;
import jsfbeans.discipline.DisciplineMB;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcusviniv on 26/09/2016.
 */

@Named
@ViewScoped
public class AulaMB implements Serializable{

    @Inject
    @LectureProxyQualifier
    private LectureServiceLocal lectureService;

    @Inject
    private IntervalUnitServiceLocal intervalUnitService;

    @Inject
    private DisciplineMB disciplineMB;

    @Inject
    private TeacherMB teacherMB;

    @Inject
    private RoomsMB roomsMB;

    @Inject
    private FacesMessagesFacade fmf;

    private List<IntervalUnit> horarioList;

    private IntervalUnit horarioSelecionado;

    private List<DayEnum> dias;

    private DayEnum diaSelecionado;

    @PostConstruct
    private void init(){
        horarioList = intervalUnitService.listIntervalUnits();
        dias = new ArrayList<>();
        dias.add(DayEnum.SEGUNDA);
        dias.add(DayEnum.TERCA);
        dias.add(DayEnum.QUARTA);
        dias.add(DayEnum.QUINTA);
        dias.add(DayEnum.SEXTA);

    }


    public void selectDia(DayEnum dia){
        diaSelecionado = dia;
    }

    public void selectHorario(IntervalUnit horario){
        horarioSelecionado = horario;
    }

    public void saveLecture(){

        Lecture aula = new Lecture();
        TeacherScheduling ts = new TeacherScheduling();
        ts.setTeacherWeekDay(diaSelecionado);
        ts.setTeacher(teacherMB.getSelectedTeacher());
        ts.setTeacherIntervalUnit(horarioSelecionado);

        RoomScheduling rs = new RoomScheduling();
        rs.setRoom(roomsMB.getSelectedRoom());
        rs.setRoomIntervalUnit(horarioSelecionado);
        rs.setRoomWeekDay(diaSelecionado);

        aula.setTeacherScheduling(ts);
        aula.setRoomScheduling(rs);
        aula.setDiscipline(disciplineMB.getSelectedDiscipline());

        try {
            lectureService.save(aula);
            fmf.successMsg("Sucesso ao cadastrar aula", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }


    public DisciplineMB getDisciplineMB() {
        return disciplineMB;
    }

    public TeacherMB getTeacherMB() {
        return teacherMB;
    }

    public RoomsMB getRoomsMB() {
        return roomsMB;
    }

    public List<IntervalUnit> getHorarioList() {
        return horarioList;
    }

    public IntervalUnit getHorarioSelecionado() {
        return horarioSelecionado;
    }

    public List<DayEnum> getDias() {
        return dias;
    }

    public DayEnum getDiaSelecionado() {
        return diaSelecionado;
    }
}
