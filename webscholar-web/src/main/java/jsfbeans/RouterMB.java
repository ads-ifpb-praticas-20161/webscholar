package jsfbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * Created by marcusviniv on 24/09/2016.
 */

@Named
@RequestScoped
public class RouterMB {


    public void addRedirectParam(String targetPage, String previous){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest servletRequest = (HttpServletRequest) externalContext.getRequest();

        if( servletRequest.getAttribute("previous") == null ){
            //redirecionar somente se nao ocorreu redirecionamento
            try {
                servletRequest.setAttribute("previous", previous);
                externalContext.dispatch(targetPage);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("JA POSSUI PREVIOUS");
        }
    }

    private Map<String, String> redirectionMap;

    private void loadRedirectionMap(){
        //ler arquivo properties
    }

    private String getCurrentPage(){
        return null;
    }

    private String isRedirectNeeded(String currentPage){
        return null;
    }

    private void forward(String page){

    }

    private void addRequestParam(String key, String value){

    }

    private boolean hasRequestParam(String key){
        return false;
    }

    @PostConstruct
    private void init(){

        /*loadRedirectionMap();



        if( hasRequestParam("previous") ){
            //o redirecionamento ja foi feito
            //nothing to do now
        }
        else{
            //o redirecionamento ainda nao foi feito

            String targetPage = isRedirectNeeded(getCurrentPage());
            //se for necessario redirecionar
            if( targetPage != null){
                addRequestParam("previous", getCurrentPage() );
                forward(targetPage);
            }
        }
        */


    }





}
