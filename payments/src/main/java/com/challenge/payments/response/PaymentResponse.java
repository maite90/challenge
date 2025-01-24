package com.challenge.payments.response;

import java.time.LocalDateTime;
import java.util.Optional;

import com.challenge.payments.entities.Payment;

public class PaymentResponse {

    private Long id;
    private int cost;
    private LocalDateTime date;
    //private Long sellingPointId;
    private SellingPointResponse sellingPointResponse;
    
    public PaymentResponse(Payment payment) {
        this.id=payment.getId();
        this.cost=payment.getCost();
        this.date=LocalDateTime.now();
        //this.sellingPointId=payment.getSellingPointId();
    }
        
    public PaymentResponse(Optional<Payment> byId) {
    }


    public SellingPointResponse getSellingPointResponse() {
        return sellingPointResponse;
    }

    public void setSellingPointResponse(SellingPointResponse sellingPointResponse) {
        this.sellingPointResponse = sellingPointResponse;
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
    
    /*public Long getSellingPointId() {
        return sellingPointId;
    }
    
    public void setSellingPointId(Long sellingPointId) {
        this.sellingPointId = sellingPointId;
    }*/

    public Long getId() {
        return id;
    }
  

}
