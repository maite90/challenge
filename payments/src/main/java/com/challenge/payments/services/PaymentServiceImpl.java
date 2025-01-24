package com.challenge.payments.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.challenge.payments.entities.Payment;
import com.challenge.payments.feignClients.SellingPointFeignClient;
import com.challenge.payments.repositories.PaymentRepository;
import com.challenge.payments.request.CreatePaymentRequest;
import com.challenge.payments.response.PaymentResponse;
import com.challenge.payments.response.SellingPointResponse;

import org.slf4j.Logger;
import reactor.core.publisher.Mono;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    WebClient webClient;

    @Autowired
    SellingPointFeignClient sellingPointFeignClient;

    private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);


    @Override
    public PaymentResponse newPayment(CreatePaymentRequest createPaymentRequest) {

        if (createPaymentRequest.getCost()==0){
            throw new IllegalArgumentException("Sorry, cost value must me present.");
        }
        if (createPaymentRequest.getSellingPointId()==null){
            throw new IllegalArgumentException("Sorry, selling Point ID must me present.");
        }
        
        Payment payment = new Payment();
        payment.setCost(createPaymentRequest.getCost());
        payment.setSellingPointId(createPaymentRequest.getSellingPointId());
        payment.setDate(LocalDateTime.now());
        payment = paymentRepository.save(payment);

        PaymentResponse paymentResponse = new PaymentResponse(payment);
        paymentResponse.setSellingPointResponse(sellingPointFeignClient.findById(payment.getSellingPointId()));


        return paymentResponse;
    }

    @Override
    public PaymentResponse getById(Long id) {
        Payment payment = paymentRepository.findById(id).get();
        PaymentResponse paymentResponse = new PaymentResponse(payment);
        
        paymentResponse.setSellingPointResponse(sellingPointFeignClient.findById(payment.getSellingPointId()));

        return paymentResponse;
    }

    @Override
    public SellingPointResponse getSellingpointById(Long sellingPointId) {
        Mono<SellingPointResponse> sellingPointResponse = webClient.get().uri("/sellingpoints/" + sellingPointId).retrieve().bodyToMono(SellingPointResponse.class);
        return sellingPointResponse.block();
    }

    @Override
    public List<SellingPointResponse> getSellingPoints() {
        List<SellingPointResponse> sellingPointResponse = webClient.get().uri("/sellingpoints").retrieve().bodyToMono(SellingPointResponse.class).flux().collectList().block();
        if (!sellingPointResponse.isEmpty()){
            return sellingPointResponse;
        }
        logger.info("There is no Selling Points yet.");
        return sellingPointResponse;
    }

    

}
