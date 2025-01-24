package com.challenge.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableFeignClients
@SpringBootApplication
public class PaymentsApplication {
	@Value("${sellingpoint.service.url}")
	private String sellingpointServiceUrl;

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

    @Bean
    WebClient webClient(){
		WebClient webClient=WebClient.builder()
			.baseUrl(sellingpointServiceUrl).build();
		
			return webClient;
	}




}
