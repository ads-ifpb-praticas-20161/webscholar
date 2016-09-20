/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.Admin;
import dac.webscholar.shared.entities.ScholarUser;
import dac.webscholar.shared.entities.Teacher;
import dac.webscholar.shared.entities.UserType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author vmvini
 */
@Startup
@Singleton
public class Initializer {

    @Inject
    private EntityManagerResource emr;

    @Inject
    private ListStrategyBuilder<ScholarUser> lsBuilder;

    @PostConstruct
    public void init() {
        
        try {
            EntityManager em = emr.getEntityManager();
            System.out.println("Initializer.init()");
            ListStrategy<ScholarUser> listStrategy = lsBuilder.createListStrategy().getListStrategy();
            System.out.println("pegou listStrategy");
            List<ScholarUser> users = listStrategy.getResultList();
            System.out.println("pegou lista de usuarios");
            if (users.isEmpty()) {
                ScholarUser user = new Admin("01753059417", "admin", "admin@admin.com", "admin");
                em.persist(user);
                
                ScholarUser user2 = new Teacher("01753059437", "teacher", "teacher@teacher.com", "teacher", true);
                em.persist(user2);

            }
        }
        catch(Exception e){
            System.out.println("OCORREU ERRO");
            System.out.println(e);
        }

    }

}
