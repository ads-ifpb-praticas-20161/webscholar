/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.interfaces.TeacherService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */

@Named
@RequestScoped
public class NewTeacher {

    @EJB
    private TeacherService userService;
    
    @Inject 
    private FacesMessagesFacade fmf;
    
    private Teacher teacher;
    
    public NewTeacher(){
        teacher = new Teacher();
    }
    
    public void sendRequest(){
        try{
            userService.create(teacher);
            fmf.successMsg("Seu pedido de cadastro foi enviado", "teacherRegisterForm");
            teacher = new Teacher();
        }
        catch(RuntimeException e){
            fmf.errorMsg("Erro: " + e.getMessage(), "teacherRegisterForm");
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    
}
