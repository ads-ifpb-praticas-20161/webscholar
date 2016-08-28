/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vmvini
 */

public class GenericRepositoryImpl<T> implements GenericRepository<T>  {

    protected final  Class<T> entityClass;
    
    
    protected EntityManager em;
    
    
    public GenericRepositoryImpl(Class<T> entityClass, EntityManager em) {
        this.entityClass = entityClass;
        this.em = em;
    }
    
    
    @PostConstruct
    public void init(){
        System.out.println("acabou de construir GenericRepositoryImpl");
        
    }
    
    @Override
    public T create(T t) {
        em.persist(t);
        return t;
    }

    @Override
    public <PK> T  read(PK id) {
       return em.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        return em.merge(t);
    }

    @Override
    public void delete(T t) {
        t = update(t);
        em.remove(t);
    }

    
}
