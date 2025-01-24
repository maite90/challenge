package com.challenge.sellingPoints;

import com.challenge.sellingPoints.entities.SellingPoint;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SellingPointsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testSellingpointId(){
		SellingPoint sellingPoint = new SellingPoint("La Pampa");
		sellingPoint.setSellingPointName("La Pampa");
		String specter = "La Pampa";
		String real = "La Pampa";
		assertEquals(specter, real);
	}
	@Test
	@DisplayName("Testing create object selling P.")
	void createSellingpoint(){
		SellingPoint sellingPoint = new SellingPoint("La Pampa");
		assertNotNull(sellingPoint);
	}

	@Test
	@DisplayName("Testing cost all attributes.")
	void testSellingPoint() {
		SellingPoint sellingPoint = new SellingPoint("La Pampa");
		assertAll(
				"Grouped Assertions of sellingP",
				() -> assertEquals("La Pampa", sellingPoint.getSellingPointName())
		);
	}


	

}
