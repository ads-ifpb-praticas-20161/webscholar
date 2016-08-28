/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;


/**
 *
 * @author vmvini
 */
public interface ListStrategyBuilder<T> {
    
    ListStrategyBuilder<T> createListStrategy();
    
    ListStrategyBuilder<T> addParameter(String name, Object value);
    
    ListStrategy<T> getListStrategy();
    
}
