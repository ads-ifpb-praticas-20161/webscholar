/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vmvini
 */
@Stateless
public class EntityManagerResource {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public EntityManager getEntityManager(){
        return entityManager;
    }
    
}
