/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vmvini
 */

@Entity @IdClass(value = IntervalUnitPK.class)
public class IntervalUnit implements Serializable {
    
    @Column(unique = true)
    private String name;
    
    @Id
    @Temporal(value=TemporalType.TIME)
    private Date initialDate;
    
    @Id
    @Temporal(value=TemporalType.TIME)
    private Date endDate;

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

    
    
    
    
}
