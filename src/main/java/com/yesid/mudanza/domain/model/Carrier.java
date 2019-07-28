package com.yesid.mudanza.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yesid.mudanza.domain.repository.TraceRepository;
import com.yesid.mudanza.domain.services.MakeMoveServices;

@Component
public class Carrier implements MakeMoveServices{
	
	private static final int MINIMUM_WEIGHT_TO_MOVE = 50;
	
	@Autowired
	TraceRepository traceRepository;

	@Override
	public List<String> makeElementTrips(List<Workday> workDays, String dni) {
		List<String> tripsMadeDay = new ArrayList<>();
		
		Consumer<Workday> workdayEach = workday -> {			
			int numberOfElements = workday.getElementsToMove().size();
			String tripsPerDay = getTripsPerDay(numberOfElements, workday);
			tripsMadeDay.add(tripsPerDay);
		};
		
		workDays.stream().forEach(workdayEach);
		traceRepository.saveTrace(dni, LocalDateTime.now());
		return tripsMadeDay;
	}
	
	public String getTripsPerDay(int numberOfElements, Workday workday) {
		AtomicInteger tripsPerDay = new AtomicInteger(0);
		AtomicInteger movedElements  = new AtomicInteger(0);
		
		Consumer<MovingElement> movingElementEach = element -> {
			if(movedElements.get() < numberOfElements) {
				if(element.getWeight() >= MINIMUM_WEIGHT_TO_MOVE) {
					movedElements.getAndIncrement();
					tripsPerDay.getAndIncrement();
				}else {
					int elementsToMove = getElementsToMove(element.getWeight());
					int remainingElementsToMove = numberOfElements - movedElements.get();
					if(remainingElementsToMove >= elementsToMove) {
						movedElements.addAndGet(elementsToMove);
						tripsPerDay.getAndIncrement();
					}
				}					
			}
			
		};
		workday.getElementsToMove().stream()
			.sorted((elem1, elem2) -> elem2.getWeight()-elem1.getWeight())
			.forEach(movingElementEach);
		
		return String.valueOf(tripsPerDay);
	}
	
	private int getElementsToMove(int weight) {
		return (int) Math.ceil((double)MINIMUM_WEIGHT_TO_MOVE / weight);
	}
	
}
