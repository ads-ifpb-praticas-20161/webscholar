/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsfbeans;

import dac.webscholar.sessionbeans.AdminAuth;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.interfaces.Authentication;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */

@Named
@SessionScoped
public class AdminSession extends UserSession {

    
    @EJB(beanName="AdminAuth")
    private Authentication auth;
    
    @Override
    protected UserType getUserType() {
        return UserType.ADMIN;
    }

    @Override
    protected Authentication getAuth() {
        return auth;
    }
    
    
}
