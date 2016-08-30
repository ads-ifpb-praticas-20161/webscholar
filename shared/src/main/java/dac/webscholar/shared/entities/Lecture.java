/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author vmvini
 */

@Entity
public class Lecture implements Serializable {
    
    private @EmbeddedId LecturePK id;
    
    @ManyToOne
    private Teacher teacher;
    
    @ManyToOne
    private CourseDisciplineWrapper courseDiscipline;
    
}
