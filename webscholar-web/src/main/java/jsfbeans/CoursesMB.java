package jsfbeans;

import dac.webscholar.sessionbeans.course.CourseProxyQualifier;
import dac.webscholar.sessionbeans.course.CourseServiceLocal;
import dac.webscholar.shared.entities.Course;
import dac.webscholar.shared.entities.CursoHorario;
import dac.webscholar.shared.entities.DayEnum;
import dac.webscholar.shared.entities.Intervalo;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.crypto.KeySelectorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by marcusviniv on 23/09/2016.
 */

@Named
@RequestScoped
public class CoursesMB {

    private Course newCourse;

    @Inject
    @CourseProxyQualifier
    private CourseServiceLocal courseService;

    @Inject
    private FacesMessagesFacade fmf;


    private List<Intervalo> intervalos;

    private List<DayEnum> dias;

    private Map<Intervalo, Map<DayEnum, CursoHorario>> mapHorario;

    @PostConstruct
    private void init(){
        newCourse = new Course();
        System.out.println("PEGAR HORARIOS");
        mapHorario = courseService.mapHorario();
        mapHorario.forEach( (t, c) -> {
            System.out.println("--------------------------------");
            System.out.println("T: " + t);
            c.forEach((d, h) -> {
                System.out.println("dia: " + d);
                System.out.println(h);
            });
        });
        System.out.println("LISTOU HORARIOS");

        intervalos = new ArrayList<>();

        for( Intervalo i : Intervalo.values()){
            intervalos.add(i);
        }

        dias = new ArrayList<>();
        for(DayEnum d : DayEnum.values()){
            dias.add(d);
        }

    }


    public void salvar(){
        try{
            courseService.saveCourse(newCourse);
            fmf.successMsg("sucesso ao cadastrar " + newCourse.getName(), null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }

    }

    public Course getNewCourse(){
        return newCourse;
    }

    public void setNewCourse(Course newCourse) {
        this.newCourse = newCourse;
    }

    public Map<Intervalo, Map<DayEnum, CursoHorario>> getMapHorario() {
        return mapHorario;
    }

    public void setMapHorario(Map<Intervalo, Map<DayEnum, CursoHorario>> mapHorario) {
        this.mapHorario = mapHorario;
    }

    public List<Intervalo> getIntervalos() {
        return intervalos;
    }

    public void setIntervalos(List<Intervalo> intervalos) {
        this.intervalos = intervalos;
    }

    public List<DayEnum> getDias() {
        return dias;
    }

    public void setDias(List<DayEnum> dias) {
        this.dias = dias;
    }
}
