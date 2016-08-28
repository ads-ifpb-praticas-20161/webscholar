/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import dac.webscholar.entities.User;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author vmvini
 */
public class RepositoryProducer {

    @Inject 
    private EntityManager em;
    
    @Produces
    public <T> GenericRepository<T> create(InjectionPoint ip){
        Annotated annotated = ip.getAnnotated(); 
        Class clazz = Object.class; 
        Type type = annotated.getBaseType(); 
        if (type instanceof ParameterizedType) { 
            ParameterizedType pt = (ParameterizedType) type; 
            clazz = (Class) pt.getActualTypeArguments()[0]; 
        }
        return new GenericRepositoryImpl(clazz, em);
    }
    
}

