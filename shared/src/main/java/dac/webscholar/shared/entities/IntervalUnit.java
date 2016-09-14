/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author vmvini
 */

@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"initialDate", "endDate"})
)

@Entity
public class IntervalUnit implements Serializable {
    
    @Id
    private String name;


    @Temporal(value=TemporalType.TIME)
    private Date initialDate;


    @Temporal(value=TemporalType.TIME)
    private Date endDate;

    public IntervalUnit(String name, Date initialDate, Date endDate) {
        this.name = name;
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    public IntervalUnit(){
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "IntervalUnit{" +
                "name='" + name + '\'' +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                '}';
    }
}
