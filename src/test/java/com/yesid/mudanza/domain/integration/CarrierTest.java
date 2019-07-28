package com.yesid.mudanza.domain.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yesid.mudanza.domain.model.Carrier;
import com.yesid.mudanza.domain.model.Workday;
import com.yesid.mudanza.domain.testdatabuilder.WorkdaysTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarrierTest {

	private static final String DNI = "1067938367";
	
	@Autowired
	Carrier carrier;
	
	@Test
	public void makeItemTripsTest() {
		//arrange
		List<String> tripsMadeDayExpected = Arrays.asList("2", "1", "2", "3", "8");
		List<Workday> workdays = new WorkdaysTestDataBuilder()
										.withItemsToMoveWorkDay1()
										.withItemsToMoveWorkDay2()
										.withItemsToMoveWorkDay3()
										.withItemsToMoveWorkDay4()
										.withItemsToMoveWorkDay5()
										.build();
		
		//act		
		List<String> tripsMadeDay = carrier.makeItemTrips(workdays, DNI);
		
		//assert
		assertEquals(tripsMadeDayExpected, tripsMadeDay);
	}
	
	@Test
	public void invalidItemWeightTest() {
		//arrange
		List<Workday> workdays = new WorkdaysTestDataBuilder()
				.withItemsToMoveWorkDay1()
				.build();
		
		workdays.stream().forEach(day -> {
			day.getItemsToMove().stream()
					.limit(1)
					.peek(item -> item.setWeight(1000))
					.forEach(item -> item.getWeight());
		});

		//act
		try {
			carrier.makeItemTrips(workdays, DNI);
			fail();
		} catch (Exception e) {
			//assert
			assertEquals(Carrier.ITEM_WEIGHT_INVALID, e.getMessage());
		}
	}
}
