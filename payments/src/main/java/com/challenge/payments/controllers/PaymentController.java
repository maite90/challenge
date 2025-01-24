package com.challenge.payments.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.payments.entities.Payment;
import com.challenge.payments.repositories.PaymentRepository;
import com.challenge.payments.request.CreatePaymentRequest;
import com.challenge.payments.response.PaymentResponse;
import com.challenge.payments.services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping ("/api")
public class PaymentController {
    
    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;
    
    //creates a brand new payment transaction
    @PostMapping("/payment")
    public PaymentResponse newPayment(@RequestBody CreatePaymentRequest createPaymentRequest) {
        return paymentService.newPayment(createPaymentRequest);
    }
    
    //Get payments info by ID
    @GetMapping("/payment/{id}")
    public PaymentResponse getById(@PathVariable Long id) {
        return paymentService.getById(id);
    }

    //get all payments info
    @GetMapping("/payments")
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }
  
    

}
