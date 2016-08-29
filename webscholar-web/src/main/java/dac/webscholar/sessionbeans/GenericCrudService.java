/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.sessionbeans;

import dac.webscholar.shared.interfaces.CrudService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author vmvini
 */
public abstract class GenericCrudService<T> implements CrudService<T> {

    private Class<T> entityClass;
    
    protected abstract void validate(T t);
    
    @PostConstruct
    public void init(){
        entityClass = GenericUtils.getSuperClassGenericType(this.getClass());
    }
    
    @PersistenceContext
    protected EntityManager em;
    
    @Override
    public T create(T t) {
        validate(t);
        em.persist(t);
        return t;
    }

    @Override
    public T find(Serializable entityId) {
        return em.find(entityClass, entityId);
    }

    @Override
    public T update(T t) {
        validate(t);
        return em.merge(t);
    }

    @Override
    public void delete(Serializable id) {
         em.remove(em.getReference(entityClass, id));
    }
    
    
    
}
