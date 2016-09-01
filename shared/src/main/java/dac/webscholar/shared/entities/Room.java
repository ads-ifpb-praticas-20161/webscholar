/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dac.webscholar.shared.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author vmvini
 */
@Entity
public class Room implements Serializable {
    
    @Column(unique=true)
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    @Id
    @GeneratedValue
    private int id;

    public Room(String nome, RoomType roomType) {
        this.nome = nome;
        this.roomType = roomType;
    }
    
    public Room(){
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
