package com.challenge.payments.request;

import java.time.LocalDateTime;

public class CreatePaymentRequest {

    private int cost;
    private LocalDateTime date;
    private Long sellingPointId;

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
