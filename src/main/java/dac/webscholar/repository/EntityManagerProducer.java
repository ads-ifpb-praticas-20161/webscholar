/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vmvini
 */

public class EntityManagerProducer {
    
    @Produces
    @PersistenceContext
    private EntityManager em;
    
}
