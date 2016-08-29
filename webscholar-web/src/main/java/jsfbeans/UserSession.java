/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.interfaces.Authentication;
import dac.webscholar.shared.interfaces.CrudService;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author vmvini
 */


public abstract class UserSession<T> implements Serializable {
    
    private String email;
    private String password;
    
    private T loggedUser;
    
    
    @Inject
    private FacesMessagesFacade facesMessagesFacade;
    
    
    public UserSession(){
        
    }
    
    protected abstract Authentication getAuth();
    
    protected abstract  CrudService getUserService();
    
    public void update(){
        loggedUser = (T)getUserService().update(loggedUser);
    }
    
    public void doLogin(){
        loggedUser = (T) getAuth().login(email, password);
        if(loggedUser != null ){
            facesMessagesFacade.successMsg("Seja bem vindo, " + ((ScholarUser) loggedUser).getName(), "loginForm");
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
    
    
    public T getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(T loggedUser) {
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
