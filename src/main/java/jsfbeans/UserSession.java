/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.entities.ScholarUser;
import dac.webscholar.entities.UserType;
import dac.webscholar.sessionbeans.Authentication;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */

@Named
@SessionScoped
public class UserSession implements Serializable {
    
    private String email;
    private String password;
    
    private ScholarUser loggedUser;
    
    @Inject
    private Authentication auth;
    
    @Inject
    private FacesMessagesFacade facesMessagesFacade;
    
    public UserSession(){
        
    }
    
    public void doLogin(){
        loggedUser = auth.login(email, password);
        if(loggedUser == null){
            facesMessagesFacade.errorMsg("Email ou senha incorretos", "loginForm");
        }
        else{
            facesMessagesFacade.errorMsg("Seja bem vindo, " + loggedUser.getName(), "loginForm");
        }
    }
    
    public void logoff(){
        loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public boolean isLogged(){
        return loggedUser != null;
    }
    
    public boolean isAdmin(){
        return loggedUser.getUserType().equals(UserType.ADMIN);
    }
    
    public boolean isTeacher(){
        return loggedUser.getUserType().equals(UserType.TEACHER);
    }
    
    public ScholarUser getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(ScholarUser loggedUser) {
        this.loggedUser = loggedUser;
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

    public Authentication getAuth() {
        return auth;
    }

    public void setAuth(Authentication auth) {
        this.auth = auth;
    }

    public FacesMessagesFacade getFacesMessagesFacade() {
        return facesMessagesFacade;
    }

    public void setFacesMessagesFacade(FacesMessagesFacade facesMessagesFacade) {
        this.facesMessagesFacade = facesMessagesFacade;
    }
    
    
    
    
    
}
