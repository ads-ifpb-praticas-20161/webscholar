/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 *
 * @author vmvini
 */
@Entity
public class WeekDay implements Serializable {
    
    @Id
    @Enumerated(EnumType.STRING)
    private DayEnum weekDay;

    public WeekDay(DayEnum weekDay) {
        this.weekDay = weekDay;
    }
    
    public WeekDay(){
        
    }

    public DayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(DayEnum weekDay) {
        this.weekDay = weekDay;
    }
    
    
}
