package com.challenge.payments.feignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.challenge.payments.response.SellingPointResponse;

@FeignClient(url="${sellingpoint.service.url}", value="sellingpoint-feign-client",
            path="/api")
public interface SellingPointFeignClient {

    @GetMapping("/sellingpoints/{id}")
    public SellingPointResponse findById(@PathVariable Long id);

    
}
