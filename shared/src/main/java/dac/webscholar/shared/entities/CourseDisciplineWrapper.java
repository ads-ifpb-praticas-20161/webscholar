/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author vmvini
 */

@Entity 
public class CourseDisciplineWrapper implements Serializable {
    
    
    private @EmbeddedId CourseDisciplinePK id;

    public CourseDisciplinePK getId() {
        return id;
    }

    public void setId(CourseDisciplinePK id) {
        this.id = id;
    }
    
    
    
}
