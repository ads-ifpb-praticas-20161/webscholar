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

public class LectureFilePK implements Serializable {
    
    
    private String fileName;
    
    private DisciplinePK discipline;
    
    private int course;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DisciplinePK getDiscipline() {
        return discipline;
    }

    public void setDiscipline(DisciplinePK discipline) {
        this.discipline = discipline;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.fileName);
        hash = 37 * hash + Objects.hashCode(this.discipline);
        hash = 37 * hash + this.course;
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
        final LectureFilePK other = (LectureFilePK) obj;
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        if (!Objects.equals(this.discipline, other.discipline)) {
            return false;
        }
        if (this.course != other.course) {
            return false;
        }
        return true;
    }
    
    
  
}
