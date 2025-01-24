package com.challenge.payments.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.payments.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>{

}
