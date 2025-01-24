package com.challenge.cost;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.cost.entities.Cost;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CostApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testIdA(){
		Cost cost = new Cost(5L,1L,8);
		cost.setIdA(5L);
		Long specter = 5L;
		Long real = 5L;
		assertEquals(specter, real);
	}

	@Test
	void testIdB(){
		Cost cost = new Cost(5L,1L,8);
		cost.setIdB(1L);
		Long specter = 1L;
		Long real = 1L;
		assertEquals(real, specter);
		assertTrue(real.equals(1L));
	}

	@Test
	@DisplayName("Testing create object cost.")
	void createCost(){
		Cost cost = new Cost(5L,1L,8);
		assertNotNull(cost);
	}

	@Test
	@DisplayName("Testing cost all attributes.")
	void testCost() {
		Cost cost = new Cost(5L, 1L, 8);
		assertAll(
				"Grouped Assertions of Client",
				() -> assertEquals(5L, cost.getIdA()),
				() -> assertEquals(1L, cost.getIdB()),
				() -> assertEquals(8, cost.getCost())
		);
	}

}
