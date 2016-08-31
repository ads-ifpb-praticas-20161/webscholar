/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author vmvini
 */


public class IntervalUnitPK implements Serializable{
    
    private Date initialDate;
    
    private Date endDate;

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
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.initialDate);
        hash = 83 * hash + Objects.hashCode(this.endDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IntervalUnitPK other = (IntervalUnitPK) obj;
        if (!Objects.equals(this.initialDate, other.initialDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        return true;
    }

    
    
    
}
