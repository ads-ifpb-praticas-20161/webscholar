package jsfbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by marcusviniv on 19/09/2016.
 */

@Named
@RequestScoped
public class LoginMB implements Serializable {

    private String email;
    private String password;

    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;

    @Inject
    private FacesMessagesFacade facesMessagesFacade;

    private ListStrategy<ScholarUser> listStrategy;

    public LoginMB(){

    }

    private void saveLoggedUserToSession(ScholarUser user){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put("user", user);
    }

    public void doLogin(){

        listStrategy = lsBuilder
                .createListStrategy()
                .<String>addParameter("email", email)
                .<String>addParameter("password", password)
                .getListStrategy();

        List<ScholarUser> users = listStrategy.getResultList();

        if(users != null && users.get(0) != null){
            saveLoggedUserToSession(users.get(0));
            facesMessagesFacade.successMsg("Seja bem vindo, " + users.get(0).getName(), null);
        }
        else{
            facesMessagesFacade.errorMsg("Email ou senha incorretos", null);
        }

    }

    public void resetValues(){
        email = null;
        password = null;
    }

    public void logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
