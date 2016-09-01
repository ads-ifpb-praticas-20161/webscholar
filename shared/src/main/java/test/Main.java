/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import dac.webscholar.shared.entities.DayEnum;
import dac.webscholar.shared.entities.WeekDay;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author vmvini
 */
public class Main {

    private static EntityManager em;

    public static void insertDays() {

        WeekDay d1 = new WeekDay(DayEnum.SEGUNDA);
        WeekDay d2 = new WeekDay(DayEnum.TERCA);
        WeekDay d3 = new WeekDay(DayEnum.QUARTA);
        WeekDay d4 = new WeekDay(DayEnum.QUINTA);
        WeekDay d5 = new WeekDay(DayEnum.SEXTA);
        
        em.getTransaction().begin();
        em.persist(d1);
        em.persist(d2);
        em.persist(d3);
        em.persist(d4);
        em.persist(d5);
        em.getTransaction().commit();
        
        

    }

    public static void configEM() {
        em = Persistence.createEntityManagerFactory("dac.webscholar_shared_jar_1.0PU").createEntityManager();
    }

    public static void main(String[] args) {

        configEM();
        
        insertDays();
            
    }

}
