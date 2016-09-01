/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vmvini
 */
/*
@Entity
public class ClassTask implements Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    private String name;
    
    private String description;
    
    @ManyToOne
    private Teacher teacher;
    
    @ManyToOne
    private Discipline discipline;
    
    @Enumerated(EnumType.STRING)
    private ClassTaskType taskType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public ClassTaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(ClassTaskType taskType) {
        this.taskType = taskType;
    }
 
}
*/