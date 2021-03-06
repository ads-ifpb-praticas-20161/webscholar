/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author vmvini
 */

@SqlResultSetMapping(
        name = "CursoHorarioMap",
        classes = @ConstructorResult(
                targetClass = CursoHorario.class,
                columns = {
                    @ColumnResult(name = "course", type = String.class),
                    @ColumnResult(name = "teacher", type=String.class),
                    @ColumnResult(name = "discipline", type=String.class),
                    @ColumnResult(name = "intervalo", type = String.class ),
                    @ColumnResult(name = "dia", type=String.class),
                        @ColumnResult(name="initialDate", type= Date.class),
                    @ColumnResult(name="endDate", type=Date.class)

                }))

@Entity
public class Course implements Serializable {

   @Id
   @GeneratedValue
   private int id;
    
   @Column(unique=true)
   private String name;

   private int seasons;

    public Course(String name, int seasons) {
        this.name = name;
        this.seasons = seasons;
    }

   
   public Course(){
       
   }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seasons=" + seasons +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }
}
