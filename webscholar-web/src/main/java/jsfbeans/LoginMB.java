package jsfbeans;

import dac.webscholar.cdiqualifiers.LoginProxyQualifier;
import dac.webscholar.sessionbeans.LoginServiceLocal;
import dac.webscholar.shared.entities.ScholarUser;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;
import java.io.Serializable;

/**
 * Created by marcusviniv on 19/09/2016.
 */

@Named
@RequestScoped
public class LoginMB implements Serializable {

    private String email;
    private String password;
    private FacesContext context;

    @Inject
    @LoginProxyQualifier
    private LoginServiceLocal loginService;

    @Inject
    private FacesMessagesFacade facesMessagesFacade;


    public LoginMB(){}

    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
    }

    private void saveLoggedUserToSession(ScholarUser user){
        context.getExternalContext().getSessionMap().put("user", user);
    }

    public void doLogin(){
        try{
            ScholarUser user = loginService.login(email, password);
            saveLoggedUserToSession(user);
            facesMessagesFacade.successMsg("Seja bem vindo, " + user.getName(), null);
        }
        catch(LoginException e){
            facesMessagesFacade.errorMsg(e.getMessage(), null);
        }

    }

    public void resetValues(){
        email = null;
        password = null;
    }

    public String logout(){
        context.getExternalContext().invalidateSession();
        return "/index.xhtml";
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
}
