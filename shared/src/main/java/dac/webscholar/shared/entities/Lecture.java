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
import javax.persistence.OneToOne;

/**
 *
 * @author vmvini
 */

@Entity @IdClass(value=LecturePK.class)
public class Lecture implements Serializable {
    
    @Id
    @OneToOne
    private RoomScheduling roomScheduling;
    
    @Id 
    @ManyToOne
    private Teacher teacher;
    
    @ManyToOne
    private Discipline discipline;
    
    
    
    
    
    /*
    um professor nao pode estar em 2 aulas que ocorrem no mesmo horario
    
    se coloco professor como chave primaria,
        entao, 2 aulas na mesma sala, no mesmo horario com professores diferentes é POSSIVEL
          
    se n coloca professor como chave primaria, entao
        2 aulas em salas diferentes no mesmo horario, com o mesmo professor é possível
    
    o que fazer para garantir que um professor nao pode dar 2 aulas ao mesmo tempo?
    
    
    se colocar: prof, day, interval como chave primaria
            entao sera possivel 2 aulas com professores diferentes no mesmo horario e na mesma sala
    
    
    e se eu criasse um wrapper para weekDay, intervalUnit e Room
    isso poderia garantir que os 3 nao iriam poder se repetir ?
    
    
    LecturePK
        wrapper | teacher
        1           1
        1           2 ERRO
        2           1
    
    */
    
    public Lecture(){
        
    }

    public Lecture(RoomScheduling roomScheduling, Teacher teacher, Discipline discipline) {
        this.roomScheduling = roomScheduling;
        this.teacher = teacher;
        this.discipline = discipline;
    }

    public RoomScheduling getRoomScheduling() {
        return roomScheduling;
    }

    public void setRoomScheduling(RoomScheduling roomScheduling) {
        this.roomScheduling = roomScheduling;
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
    
    
}
