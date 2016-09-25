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
public class TeacherMB implements Serializable{


    private Teacher newTeacher;

    @Inject
    @TeacherProxyQualifier
    private TeacherServiceLocal teacherService;

    @Inject
    private FacesMessagesFacade fmf;


    @PostConstruct
    private void init(){
        newTeacher = new Teacher();
    }

    public void saveTeacher(){
        try{
            teacherService.saveTeacher(newTeacher);
            fmf.successMsg("Você receberá um email com a resposta do administrador", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    public Teacher getNewTeacher() {
        return newTeacher;
    }

    public void setNewTeacher(Teacher newTeacher) {
        this.newTeacher = newTeacher;
    }
}
