/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.jpa.JpaQuery;

/**
 *
 * @author vmvini
 */

public class ListStrategyBuilderImpl<T> implements ListStrategyBuilder<T> {

    
    private CriteriaBuilder cbuilder;
    private CriteriaQuery<T> cquery;
    private Root<T> root;
    
    private List<Predicate> predicates;

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
       
       predicates = new ArrayList<>();
       
       System.out.println("selecionou o root");
       return this;
    }

    @Override
    public <R> ListStrategyBuilder<T> addParameter(String name, R value) {
        
        predicates.add( cbuilder.equal(root.get(name), value) );
        System.out.println("Adicionou predicate a lista de predicates");
        
        return this;
    
    }

    @Override
    public ListStrategy<T> getListStrategy() {
        
        configQueryParameters();
        System.out.println("configurou query parameters");
        
        System.out.println("retornando ListStrategy");
        
        return new ListStrategy<T>(){

            @Override
            public List<T> getResultList() {
                System.out.println("retornando resultados da busca");
                TypedQuery<T> tq = em.createQuery(cquery);
                
                return tq.getResultList();
            }

            @Override
            public T getSingleResult(){
                TypedQuery<T> tq = em.createQuery(cquery);
                return tq.getSingleResult();
            }
            
        };
    }
    
    private void configQueryParameters(){
        Predicate finalPredicate;
        
        if(predicates != null && !predicates.isEmpty()){
            if(predicates.size() > 1){
               
                finalPredicate = cbuilder.and( listToArray(predicates) );
                System.out.println("Adicionou condicao ao AND");
            }
            else{
                finalPredicate = predicates.get(0);
            }
            
            cquery.select(root)
                    .where(finalPredicate);
            
            
        }
    }
    
    private Predicate[] listToArray(List<Predicate> predicates){
        int i = 0;
        if(!predicates.isEmpty()){
            
            Predicate[] arr = new Predicate[predicates.size()];
            
            Iterator<Predicate> it = predicates.iterator();
            while(it.hasNext()){
                arr[i++] = it.next();
            }
            return arr;
        }
        return new Predicate[0];
    }
    

}
