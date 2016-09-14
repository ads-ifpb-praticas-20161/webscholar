/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 *
 * @author vmvini
 */

@Entity @IdClass(value=DisciplinePK.class)
public class Discipline implements Serializable {
    
    
    @Id
    private String name;
    
    @Id
    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Season season;

    public Discipline(String name, Course course, Season season) {
        this.name = name;
        this.course = course;
        this.season = season;
    }

    public Discipline(){
        
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "name='" + name + '\'' +
                ", course=" + course +
                ", season=" + season +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
    
}
