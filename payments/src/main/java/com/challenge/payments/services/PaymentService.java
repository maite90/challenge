package com.challenge.payments.services;

import java.util.List;

import com.challenge.payments.request.CreatePaymentRequest;
import com.challenge.payments.response.PaymentResponse;
import com.challenge.payments.response.SellingPointResponse;


public interface PaymentService {

    public PaymentResponse newPayment(CreatePaymentRequest createPaymentRequest);

    public PaymentResponse getById(Long id);

    public SellingPointResponse getSellingpointById(Long sellingPointId);

    public List<SellingPointResponse> getSellingPoints();


}
