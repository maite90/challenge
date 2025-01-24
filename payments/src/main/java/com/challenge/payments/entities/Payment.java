package com.challenge.payments.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    private int cost;
    private LocalDateTime date;
    @Column(name="selling_point_id")
    private Long sellingPointId;
    
    public Payment() {
    }
   
    public Payment(int cost, LocalDateTime date, Long sellingPointId) {
        this.cost = cost;
        this.date = date;
        this.sellingPointId = sellingPointId;
    }
    
    public Long getId() {
        return id;
    }

    public int getCost() {
        return cost;
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public Long getSellingPointId() {
        return sellingPointId;
    }
    
    public void setSellingPointId(Long sellingPointId) {
        this.sellingPointId = sellingPointId;
    }

    


}
