/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 *
 * @author vmvini
 */

@Entity
public class ScholarUser implements Serializable {
    
    @Id
    private String cpf;
    private String name;
    @Column(unique=true)
    private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public ScholarUser(String cpf, String name, String email, String password, UserType userType) {
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }
 
    public ScholarUser(){
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "ScholarUser{" + "cpf=" + cpf + ", name=" + name + ", email=" + email + ", password=" + password + ", userType=" + userType + '}';
    }
    
    
    
    
}
