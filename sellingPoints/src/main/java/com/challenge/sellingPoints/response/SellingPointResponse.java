package com.challenge.sellingPoints.response;

import com.challenge.sellingPoints.entities.SellingPoint;

public class SellingPointResponse {

    private Long id;
    private String sellingPointName;
     
    
    public SellingPointResponse(SellingPoint sellingPoint) {
        this.id=sellingPoint.getId();
        this.sellingPointName=sellingPoint.getSellingPointName();
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
