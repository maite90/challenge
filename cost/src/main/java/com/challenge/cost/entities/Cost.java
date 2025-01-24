package com.challenge.cost.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cost")
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @Column(name="id_a")    
    private Long idA;
    @Column(name="id_b")
    private Long idB;
    private int cost;
    
    public Cost() {
    }

    public Cost(Long idA, Long idB, int cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

    public Cost(Long id, Long idA, Long idB, int cost) {
        this.id = id; 
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

    public Cost(Long idA, int cost) {
        this.idA = idA;
        this.cost = cost;
    }

    public Long getIdA() {
        return idA;
    }

    public Long getIdB() {
        return idB;
    }

    public int getCost() {
        return cost;
    }

    public void setIdA(Long idA) {
        this.idA = idA;
    }

    public void setIdB(Long idB) {
        this.idB = idB;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }  
    

}
