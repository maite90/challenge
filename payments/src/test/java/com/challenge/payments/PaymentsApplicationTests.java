package com.challenge.payments;

import com.challenge.payments.entities.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testCost(){
		Payment payment = new Payment(2, LocalDateTime.now(),  8L);
		payment.setCost(2);
		int specter = 2;
		int real = 2;
		assertEquals(specter, real);
	}

	@Test
	void testSellingpointId(){
		Payment payment = new Payment(2, LocalDateTime.now(),  8L);
		payment.setSellingPointId(8L);
		Long specter = 8L;
		Long real = 8L;
		assertEquals(real, specter);
		assertTrue(real.equals(8L));
	}

	@Test
	@DisplayName("Testing create object cost.")
	void createPayment(){
		Payment payment = new Payment(2, LocalDateTime.now(),  8L);
		assertNotNull(payment);
	}

	@Test
	@DisplayName("Testing cost all attributes.")
	void testPayment() {
		Payment payment = new Payment(2, LocalDateTime.now(),  8L);
		assertAll(
				"Grouped Assertions of Client",
				() -> assertEquals(2, payment.getCost()),
				() -> assertEquals(LocalDateTime.now(), payment.getDate()),
				() -> assertEquals(8L, payment.getSellingPointId())
		);
	}


}
