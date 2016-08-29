/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.Entity;

/**
 *
 * @author vmvini
 */

@Entity
public class Teacher extends ScholarUser {
    
    private boolean activated;
    
    public Teacher(){
        userType = UserType.TEACHER;
        activated = false;
    }

    public Teacher(String cpf, String name, String email, String password) {
        super(cpf, name, email, password, UserType.TEACHER);
        activated = false;
    }
    
    public Teacher(String cpf, String name, String email, String password, boolean activated) {
        super(cpf, name, email, password, UserType.TEACHER);
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    
    
    
    
    
    
}
