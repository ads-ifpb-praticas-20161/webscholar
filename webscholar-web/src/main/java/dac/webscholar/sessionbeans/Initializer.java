/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.repository.ListStrategy;
import dac.webscholar.repository.ListStrategyBuilder;
import dac.webscholar.shared.entities.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vmvini
 */
@Startup
@Singleton
public class Initializer {

    @PersistenceContext
    private EntityManager em;

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
                ScholarUser user = new Admin("01753059417", "admin", "admin@gmail.com", "admin");
                em.persist(user);
                
                ScholarUser user2 = new Teacher("01753059437", "teacher", "teacher@gmail.com", "teacher", true);
                em.persist(user2);

            }

            initTimeUnits();

        }
        catch(Exception e){
            System.out.println("OCORREU ERRO");
            System.out.println(e);
        }

    }

    private Date createTime(int hour, int minute){
        Calendar date = Calendar.getInstance();
        date.set(Calendar.DATE, 0);
        date.set(Calendar.HOUR, hour);
        date.set(Calendar.MINUTE, minute);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.set(Calendar.MONTH, 0);
        date.set(Calendar.YEAR, 1970);

        //date.setTimeZone(TimeZone.getDefault());

        return date.getTime();
    }


    private void initTimeUnits(){

        List<IntervalUnit> intervalUnits = new ArrayList<>();

        intervalUnits.add( new IntervalUnit("M1", createTime(19, 0), createTime(19, 50)) );
        intervalUnits.add( new IntervalUnit("M2", createTime(19,50 ), createTime(20,40 )) );
        intervalUnits.add( new IntervalUnit("M3", createTime(20,40 ), createTime(21,30 )) );
        intervalUnits.add( new IntervalUnit("M4",createTime(21,45 ), createTime(22,35 )) );
        intervalUnits.add( new IntervalUnit("M5", createTime(22,25 ), createTime(23,25 )) );
        intervalUnits.add( new IntervalUnit("M6", createTime(23,25 ), createTime(24,15 )) );

        intervalUnits.add( new IntervalUnit("T1", createTime(1, 0 ), createTime(1, 50 )) );
        intervalUnits.add( new IntervalUnit("T2", createTime(1, 50 ), createTime(2,40 )) );
        intervalUnits.add( new IntervalUnit("T3", createTime(2,40 ), createTime(3,30 )) );
        intervalUnits.add( new IntervalUnit("T4", createTime(3,45 ), createTime(4,35 )) );
        intervalUnits.add( new IntervalUnit("T5", createTime(4,35 ), createTime(5,25 )) );
        intervalUnits.add( new IntervalUnit("T6", createTime(5,25 ), createTime(6,15 )) );

        intervalUnits.add( new IntervalUnit("N1", createTime(6, 50 ), createTime(7,40 )) );
        intervalUnits.add( new IntervalUnit("N2", createTime(7,40 ), createTime(8,30 )) );
        intervalUnits.add( new IntervalUnit("N3", createTime(8,40 ), createTime(9,30 )) );
        intervalUnits.add( new IntervalUnit("N4", createTime(9,30 ), createTime(10,20 )) );
        intervalUnits.add( new IntervalUnit("N5", createTime(10,20 ), createTime(11,10 )) );


        intervalUnits.forEach(i -> {
            em.persist(i);
        });

    }


}
