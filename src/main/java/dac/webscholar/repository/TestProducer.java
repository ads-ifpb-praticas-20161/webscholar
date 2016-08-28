/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

/**
 *
 * @author vmvini
 */
public class TestProducer {
    
    @Produces
    @RequestScoped
    public TestInterface create(){
        return new Test("vmvini");
    }
    
}
