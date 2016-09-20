package jsfbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private FacesContext context;

    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;

    @Inject
    private FacesMessagesFacade facesMessagesFacade;

    private ListStrategy<ScholarUser> listStrategy;

    public LoginMB(){

    }

    @PostConstruct
    public void init(){
        context = FacesContext.getCurrentInstance();
    }

    private void saveLoggedUserToSession(ScholarUser user){
        context.getExternalContext().getSessionMap().put("user", user);
    }

    public void doLogin(){

        listStrategy = lsBuilder
                .createListStrategy()
                .<String>addParameter("email", email)
                .<String>addParameter("password", password)
                .getListStrategy();

        List<ScholarUser> users = listStrategy.getResultList();

        if(users != null && users.size() == 1){
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

    public String logout(){
        context.getExternalContext().invalidateSession();
        //HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
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
