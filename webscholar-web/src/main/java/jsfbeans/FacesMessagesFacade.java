/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author vmvini
 */
public class FacesMessagesFacade implements Serializable {
    
    public void errorMsg(String msg, String uiComponent){
       FacesContext.getCurrentInstance().addMessage(uiComponent, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
    
    public void successMsg(String msg, String uiComponent){
       FacesContext.getCurrentInstance().addMessage(uiComponent, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }
    
}
