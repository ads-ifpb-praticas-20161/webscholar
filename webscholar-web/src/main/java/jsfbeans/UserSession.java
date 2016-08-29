/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.sessionbeans.EntityManagerResource;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.interfaces.Authentication;
import dac.webscholar.shared.interfaces.UserService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author vmvini
 */


public abstract class UserSession implements Serializable {
    
    private String email;
    private String password;
    
    private ScholarUser loggedUser;
    
    
    @Inject
    private FacesMessagesFacade facesMessagesFacade;
    
    @EJB
    private UserService userService;
    
    public UserSession(){
        
    }
    
    protected abstract Authentication getAuth();
    
    
    public void update(){
        loggedUser = userService.update(loggedUser);
    }
    
    public void doLogin(){
        loggedUser = getAuth().login(email, password);
        if(loggedUser != null ){
            facesMessagesFacade.successMsg("Seja bem vindo, " + loggedUser.getName(), "loginForm");
        }
        else{
            facesMessagesFacade.errorMsg("Email ou senha incorretos", "loginForm");
        }
    }
    
    protected abstract UserType getUserType();
    
    public void logoff(){
        loggedUser = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public boolean isLogged(){
        return loggedUser != null;
    }
    
    public boolean isAdmin(){
        if( isLogged() )
            return loggedUser.getUserType().equals(UserType.ADMIN);
        return false;
    }
    
    public boolean isTeacher(){
        if( isLogged() )
            return loggedUser.getUserType().equals(UserType.TEACHER);
        return false;
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

    public FacesMessagesFacade getFacesMessagesFacade() {
        return facesMessagesFacade;
    }

    public void setFacesMessagesFacade(FacesMessagesFacade facesMessagesFacade) {
        this.facesMessagesFacade = facesMessagesFacade;
    }
    
    
    
    
    
}
