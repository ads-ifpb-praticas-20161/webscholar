/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author vmvini
 */

public class ListStrategyBuilderImpl<T> implements ListStrategyBuilder<T> {

    
    private CriteriaBuilder cbuilder;
    private CriteriaQuery<T> cquery;
    private Root<T> root;
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    private final Class<T> entityClass;
    
    public ListStrategyBuilderImpl(Class<T> entityClass){
        this.entityClass = entityClass;
    }
    
    @PostConstruct
    public void init(){
        System.out.println("Acabou de construir ListStrategyBuilderImpl");
    }
    
    @Override
    public ListStrategyBuilder<T> createListStrategy() {
       cbuilder = em.getCriteriaBuilder();
       cquery = cbuilder.createQuery(entityClass);
       root = cquery.from(entityClass);
       cquery.select(root);
       return this;
    }

    @Override
    public ListStrategyBuilder<T> addParameter(String name, Object value) {
        return this;
    }

    @Override
    public ListStrategy<T> getListStrategy() {
        System.out.println("retornando ListStrategy");
        return new ListStrategy<T>(){

            @Override
            public List<T> getResultList() {
                System.out.println("retornando resultados da busca");
                TypedQuery<T> tq = em.createQuery(cquery);
                return tq.getResultList();
            }
            
        };
    }

}
