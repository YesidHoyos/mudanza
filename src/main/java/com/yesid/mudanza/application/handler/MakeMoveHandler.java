package com.yesid.mudanza.application.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yesid.mudanza.application.exception.UnexpectedException;
import com.yesid.mudanza.application.factory.WorkdayFactory;
import com.yesid.mudanza.domain.model.Carrier;
import com.yesid.mudanza.domain.model.Workday;

@Component
public class MakeMoveHandler {

	@Autowired
	WorkdayFactory 	workdayFactory;
	
	@Autowired
	Carrier carrier;
	
	private static final String LINE_FORMAT = "Case #%s: %s\n";
	
	public byte[] makeElementTrips(MultipartFile input, String dni) {
		List<Workday> workDays = workdayFactory.create(input);
		List<String> tripsMadeDay = carrier.makeItemTrips(workDays, dni);
		return getBytes(tripsMadeDay);
	}
	
	private byte[] getBytes(List<String> tripsMadeDay) {
		ByteArrayOutputStream tripsMadeDayByte = new ByteArrayOutputStream();
		AtomicInteger trip = new AtomicInteger(1);
		
		tripsMadeDay.stream().forEach(line -> {
			String tripsLine = String.format(LINE_FORMAT, trip.get(), line);			
			try {
				tripsMadeDayByte.write(tripsLine.getBytes());
			} catch (IOException e) {
				throw new UnexpectedException(WorkdayFactory.UNEXPECTED_EXCEPTION);
			}
			trip.getAndIncrement();
		});
		return tripsMadeDayByte.toByteArray();
	}
}
