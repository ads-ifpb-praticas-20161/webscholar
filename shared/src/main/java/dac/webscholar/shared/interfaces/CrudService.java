/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.interfaces;

import java.io.Serializable;

/**
 *
 * @author vmvini
 */
public interface CrudService<T> {
    
    public T create(T t);
    public T find(Serializable entityId);
    public T update(T t);
    public void delete(Serializable id);
    
}
