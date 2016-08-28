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
import javax.inject.Inject;
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
    
    
    private EntityManager em;
    
    
    private final Class<T> entityClass;
    
    public ListStrategyBuilderImpl(Class<T> entityClass, EntityManager em){
        this.entityClass = entityClass;
        this.em = em;
    }
    
    @PostConstruct
    public void init(){
        System.out.println("Acabou de construir ListStrategyBuilderImpl");
    }
    
    @Override
    public ListStrategyBuilder<T> createListStrategy() {
       System.out.println("criando estrategia de list");
       cbuilder = em.getCriteriaBuilder();
       System.out.println("criou o builder");
       cquery = cbuilder.createQuery(entityClass);
       System.out.println("criou a query");
       root = cquery.from(entityClass);
       System.out.println("criou o root");
       cquery.select(root);
       System.out.println("selecionou o root");
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
