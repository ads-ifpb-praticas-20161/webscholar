/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;
import dac.webscholar.shared.interfaces.Authentication;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vmvini
 */

@Stateless
@Remote(Authentication.class)
public class AuthenticationImpl implements Authentication {
    
    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;
    
    private ListStrategy<ScholarUser> listStrategy;
    
    @Override
    public ScholarUser login(String email, String password, UserType userType){
        System.out.println("email: '" + email + "'");
        System.out.println("password: '" + password + "'");
        System.out.println("tipo: '" + userType + "'");
        
        listStrategy = lsBuilder
                        .createListStrategy()
                        .<String>addParameter("email", email)
                        .<String>addParameter("password", password)
                        .<UserType>addParameter("userType", userType)
                        .getListStrategy();
        
        List<ScholarUser> users = listStrategy.getResultList();
        
        if(users.isEmpty()){
            System.out.println("NENHUM USUARIO ENCONTRADO");
            return null;
        }
        
        System.out.println("USUARIO ENCONTRADO");
        System.out.println(users.get(0));
        
        return users.get(0);
    }
    
    
    
}
