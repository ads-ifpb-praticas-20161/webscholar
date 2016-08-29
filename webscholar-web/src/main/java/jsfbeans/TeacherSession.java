/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.sessionbeans.TeacherAuth;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.interfaces.Authentication;
import dac.webscholar.shared.interfaces.CrudService;
import dac.webscholar.shared.interfaces.TeacherService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */
@Named
@SessionScoped
public class TeacherSession extends UserSession<Teacher>{

    @EJB 
    private TeacherService teacherService;
    
    @EJB(beanName="TeacherAuth")
    private Authentication teacherAuth;
    
    @Override
    protected UserType getUserType() {
        return UserType.TEACHER;
    }

    @Override
    protected Authentication getAuth() {
        return teacherAuth;
    }

    @Override
    protected CrudService getUserService() {
        return teacherService;
    }
  
}
