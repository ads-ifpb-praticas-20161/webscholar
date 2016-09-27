package jsfbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by marcusviniv on 24/09/2016.
 */

@Named
@RequestScoped
public class TestMB {

    private SecureRandom random = new SecureRandom();

    public String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

    @PostConstruct
    private void init(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();

        externalContext.getRequestMap().put("param", nextSessionId());

        //LOAD PROPERTIES FILE
        //BUILD MAP
        // IF NEED REDIRECT
                //ADD NEXT PAGE REQ PARAM (PAG DE LISTAR)
                //REDIRECT
        //ELSE
            //WAIT USER SELECT
                //ACTION-> MB.SELECT(){
                        //  ADD REQ PARAM SELECT
                        // GO TO NEXT PAGE LINK SAVED IN REQ PARAM
                //



    }

}
