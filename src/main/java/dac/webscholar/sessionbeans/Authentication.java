/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.entities.ScholarUser;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author vmvini
 */

@Stateless
public class Authentication {
    
    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;
    
    private ListStrategy<ScholarUser> listStrategy;
    
    public ScholarUser login(String email, String password){
        System.out.println("email: '" + email + "'");
        System.out.println("password: '" + password + "'");
        
        listStrategy = lsBuilder
                        .createListStrategy()
                        .<String>addParameter("email", email)
                        .<String>addParameter("password", password)
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
