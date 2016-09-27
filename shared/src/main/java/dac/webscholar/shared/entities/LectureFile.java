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
        @UniqueConstraint(columnNames={"fileName", "discipline"})
)
@Entity
public class LectureFile implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String fileName;
    
    private String filePath;

    @ManyToOne
    private Discipline discipline;

    
    @ManyToOne
    private Teacher teacher;

    
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}