/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author vmvini
 */
public class DisciplinePK implements Serializable {
    
    
    private String name;
    
    private int course;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public DisciplinePK(String name, int course) {
        this.name = name;
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + this.course;
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
        final DisciplinePK other = (DisciplinePK) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.course != other.course) {
            return false;
        }
        return true;
    }

    
    
    
    
    
}
