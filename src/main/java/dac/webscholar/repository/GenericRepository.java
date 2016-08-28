/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author vmvini
 */

public interface GenericRepository<T> {
    
   
   T create(T t);
  /* <PK>  T  read(PK  id);*/
   T update(T t);
   void delete(T t);
  
    
}