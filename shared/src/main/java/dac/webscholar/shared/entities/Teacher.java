/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author vmvini
 */

@Entity
public class Teacher extends ScholarUser {

    @Column(nullable = true)
    private boolean activated;

    public Teacher(){
        this.activated = false;
        this.userType = UserType.TEACHER;
    }

    public Teacher(boolean activated) {
        this.activated = activated;
        userType = UserType.TEACHER;
    }

    public Teacher(String cpf, String name, String email, String password, boolean activated) {
        super(cpf, name, email, password);
        this.activated = activated;
        userType = UserType.TEACHER;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "activated=" + activated +
                '}';
    }
}
