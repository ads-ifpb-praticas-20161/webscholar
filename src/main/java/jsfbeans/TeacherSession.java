/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.entities.UserType;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */
@Named
@SessionScoped
public class TeacherSession extends UserSession{

    @Override
    protected UserType getUserType() {
        return UserType.TEACHER;
    }
  
}
