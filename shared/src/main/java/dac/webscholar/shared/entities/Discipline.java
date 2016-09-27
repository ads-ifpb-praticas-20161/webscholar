/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author vmvini
 */

@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "course"})
)
@Entity
public class Discipline implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String name;


    @ManyToOne
    private Course course;

    private int season;

    public Discipline(String name, Course course, int season) {
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

    public int getSeason() {
        return season;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeason(int season) {
        this.season = season;
    }
    
}
