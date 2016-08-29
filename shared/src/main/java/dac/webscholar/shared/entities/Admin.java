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
public class Admin extends ScholarUser{
    
    public Admin(){
        userType = UserType.ADMIN;
    }

    public Admin(String cpf, String name, String email, String password) {
        super(cpf, name, email, password, UserType.ADMIN);
    }
    
    
    
}
