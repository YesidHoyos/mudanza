package com.yesid.mudanza.domain.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yesid.mudanza.domain.model.Carrier;
import com.yesid.mudanza.domain.model.Item;
import com.yesid.mudanza.domain.model.Workday;
import com.yesid.mudanza.domain.repository.TraceRepository;
import com.yesid.mudanza.domain.testdatabuilder.WorkdaysTestDataBuilder;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarrierTest {

	private static final String DNI = "1067938367";

	@Mock
	TraceRepository traceRepository;
	
	@InjectMocks
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
		when(traceRepository.saveTrace(Mockito.anyString(), Mockito.any())).thenReturn("");
		//act		
		List<String> tripsMadeDay = carrier.makeItemTrips(workdays, DNI);
		
		//assert
		assertEquals(tripsMadeDayExpected, tripsMadeDay);
	}
	
	@Test
	public void validateWorkdaysTest() {
		//arrange
		int numberWorkdaysInvalid = 501;
		
		try {
			//act
			carrier.validateWorkdays(numberWorkdaysInvalid);
			fail();
		} catch (Exception e) {
			assertEquals(Carrier.NUMBER_WORKDAYS_INVALID, e.getMessage());
		}		
	}
	
	@Test
	public void validateItemsToMovePerWorkdayTest() {
		//arrange
		int numberMovingItemsPerWorkdayInvalid = 101;
		
		try {
			//act
			carrier.validateItemsToMovePerWorkday(numberMovingItemsPerWorkdayInvalid);
			fail();
		} catch (Exception e) {
			assertEquals(Carrier.NUMBER_MOVING_ITEMS_PER_WORKDAY_INVALID, e.getMessage());
		}		
	}
	
	@Test
	public void validateItemTest() {
		//arrange
		int weightInvalid = 101;
		Item itemInvalid = new Item(weightInvalid); 
		
		try {
			//act
			carrier.validateItem(itemInvalid);
			fail();
		} catch (Exception e) {
			assertEquals(Carrier.ITEM_WEIGHT_INVALID, e.getMessage());
		}		
	}
	
	@Test
	public void getTripsPerDayTest() {
		//arrange
		String tripsPerDayExpected = "2";
		int numberOfItems = 4;
		Workday workday = new WorkdaysTestDataBuilder()
						.withItemsToMoveWorkDay1()
						.build()
						.stream()
						.findFirst()
						.orElse(null);
		
		//act
		String tripsPerDay = carrier.getTripsPerDay(numberOfItems, workday);
		
		//assert
		assertEquals(tripsPerDayExpected, tripsPerDay);
	}
	
}
