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

    public LectureFilePK(){}

    public LectureFilePK(String fileName, DisciplinePK discipline) {
        this.fileName = fileName;
        this.discipline = discipline;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LectureFilePK that = (LectureFilePK) o;

        if (!discipline.equals(that.discipline)) return false;
        if (!fileName.equals(that.fileName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + discipline.hashCode();
        return result;
    }
}
