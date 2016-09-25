package jsfbeans;

import dac.webscholar.sessionbeans.teacher.TeacherProxyQualifier;
import dac.webscholar.sessionbeans.teacher.TeacherServiceLocal;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 25/09/2016.
 */
@Named
@ViewScoped
public class InactiveTeachersMB implements Serializable {

    @Inject
    @TeacherProxyQualifier
    private TeacherServiceLocal teacherService;

    private List<Teacher> teachers;

    private Teacher selected;

    @Inject
    private FacesMessagesFacade fmf;

    @PostConstruct
    private void init(){
        teachers = teacherService.getInactiveTeachers();
    }

    public void activateTeacher(){
        try {
            //esse serviço ja envia mensagem jms (para atualizar no banco e enviar email)
            teacherService.activateTeacher(selected);

            teachers = teacherService.getInactiveTeachers();

            fmf.successMsg("sucesso ao ativar professor", null);

        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public void selectTeacher(Teacher t){
        System.out.println("selecionou um professor");
        selected = t;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }


    public Teacher getSelected() {
        return selected;
    }

    public void setSelected(Teacher selected) {
        this.selected = selected;
    }
}
