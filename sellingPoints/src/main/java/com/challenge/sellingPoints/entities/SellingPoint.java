package com.challenge.sellingPoints.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sellingpoint")
public class SellingPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="selling_point_name")
    private String sellingPointName;

    public SellingPoint() {
    }

    public SellingPoint(String sellingPointName) {
        this.sellingPointName = sellingPointName;
    }

    public Long getId() {
        return id;
    }

    public String getSellingPointName() {
        return sellingPointName;
    }

    public void setSellingPointName(String sellingPointName) {
        this.sellingPointName = sellingPointName;
    }


    
}
