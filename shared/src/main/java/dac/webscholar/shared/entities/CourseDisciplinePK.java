/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author vmvini
 */

@Embeddable
public class CourseDisciplinePK {
    
    private Course course;
    
    private Discipline discipline;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    
    
    
    
    
}
