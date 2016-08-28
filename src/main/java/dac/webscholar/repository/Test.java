/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.repository;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author vmvini
 */


public class Test implements TestInterface {
    
    private final String name;
    public Test(String name){
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

   
    
    
}
