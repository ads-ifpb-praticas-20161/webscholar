package jsfbeans;

import dac.webscholar.sessionbeans.teacher.TeacherProxyQualifier;
import dac.webscholar.sessionbeans.teacher.TeacherServiceLocal;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.exceptions.ValidationException;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
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

    private Teacher searchTeacher;

    private Teacher selectedTeacher;

    private List<Teacher> teachers;

    private Teacher beforeUpdate;

    @PostConstruct
    private void init(){
        searchTeacher = new Teacher();
        newTeacher = new Teacher();
        teachers = teacherService.listAll();

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

    public void select(Teacher teacher){
        beforeUpdate = new Teacher();
        selectedTeacher = teacher;
        backupTeacher(selectedTeacher, beforeUpdate);
    }

    public void findTeacher(){
       try {
           Teacher teacher = teacherService.searchByCpf(searchTeacher.getCpf());
           teachers.clear();
           teachers.add(teacher);
        }
       catch(ValidationException e){
           try {
               teachers = teacherService.searchByName(searchTeacher.getName());
           }
           catch(ValidationException ex){
               fmf.errorMsg(ex.getMessage(), null);
           }
       }

    }

    public Teacher getSearchTeacher() {
        return searchTeacher;
    }

    public void setSearchTeacher(Teacher searchTeacher) {
        this.searchTeacher = searchTeacher;
    }

    public void updateTeacher(){

        try{
            teacherService.updateTeacher(selectedTeacher);
            fmf.successMsg("sucesso ao atualizar", null);
        }
        catch(ValidationException e){
            backupTeacher(beforeUpdate, selectedTeacher);
            fmf.errorMsg(e.getMessage(), null);
        }
    }

    private void backupTeacher(Teacher source, Teacher target) {
        target.setActivated(source.isActivated());
        target.setName(source.getName());
        target.setCpf(source.getCpf());
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
    }

    public void removeTeacher(){
        try{
            teacherService.removeTeacher(selectedTeacher);
            teachers = teacherService.listAll();
            fmf.successMsg("sucesso ao remover professor", null);
        }
        catch(ValidationException e){
            fmf.errorMsg(e.getMessage(), null);
        }
    }



    public Teacher getSelectedTeacher() {
        return selectedTeacher;
    }

    public void setSelectedTeacher(Teacher selectedTeacher) {
        this.selectedTeacher = selectedTeacher;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Teacher getNewTeacher() {
        return newTeacher;
    }

    public void setNewTeacher(Teacher newTeacher) {
        this.newTeacher = newTeacher;
    }
}
