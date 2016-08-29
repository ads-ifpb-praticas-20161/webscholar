/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.repository.GenericRepository;
import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.UserType;
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

    @Inject
    private GenericRepository<ScholarUser> userRepository;

    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;

    @PostConstruct
    public void init() {
        try {
            System.out.println("Initializer.init()");
            ListStrategy<ScholarUser> listStrategy = lsBuilder.createListStrategy().getListStrategy();
            System.out.println("pegou listStrategy");
            List<ScholarUser> users = listStrategy.getResultList();
            System.out.println("pegou lista de usuarios");
            if (users.isEmpty()) {
                ScholarUser user = new ScholarUser("01753059417", "admin", "admin@admin.com", "admin", UserType.ADMIN);
                userRepository.create(user);
                
                ScholarUser user2 = new ScholarUser("01753059437", "teacher", "teacher@teacher.com", "teacher", UserType.TEACHER);
                userRepository.create(user2);

            }
        }
        catch(Exception e){
            System.out.println("OCORREU ERRO");
            System.out.println(e);
        }

    }

}
