package com.yesid.mudanza.domain.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yesid.mudanza.domain.model.Carrier;
import com.yesid.mudanza.domain.repository.TraceRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarrierTest {

	@Mock
	TraceRepository traceRepository;
	
	@InjectMocks
	Carrier carrier;
	
	@Test
	public void getTripsPerDayTest() {
		
	}
	
}
