/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 *
 * @author vmvini
 */
public class Day {
    
    @Id
    private int id;
    
    @Enumerated(EnumType.STRING)
    private DayEnum day;

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public DayEnum getDay(){
        return day;
    }

    public void setDay(DayEnum day) {
        this.day = day;
    }
    
}
