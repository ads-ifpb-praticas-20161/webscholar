/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.entities.User;
import dac.webscholar.repository.GenericRepository;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.repository.Test;
import dac.webscholar.repository.TestInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author vmvini
 */
@Startup
@Singleton
public class Initializer {
    
    //@Inject
    //private GenericRepository<User> userRepository;

    //@Inject
    //private ListStrategyBuilder<User> lsBuilder;

    @Inject
    private TestInterface test;
    
    @PostConstruct
    public void init() {
        System.out.println(test.getName());
        /*System.out.println("Initializer.init()");
        ListStrategy<User> listStrategy = lsBuilder.createListStrategy().getListStrategy();
        System.out.println("pegou listStrategy");
        List<User> users = listStrategy.getResultList();
        System.out.println("pegou lista de usuarios");
        if (users.isEmpty()) {
            User user = new User("01753059417", "admin", "admin@admin.com", "admin");
            userRepository.create(user);

        }*/
    }

}