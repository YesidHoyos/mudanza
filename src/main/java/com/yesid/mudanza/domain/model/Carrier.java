package com.yesid.mudanza.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yesid.mudanza.domain.exception.OutOfRangeException;
import com.yesid.mudanza.domain.repository.TraceRepository;
import com.yesid.mudanza.domain.services.MakeMoveServices;

@Component
public class Carrier implements MakeMoveServices{
	
	public static final String ITEM_WEIGHT_INVALID = "El peso de un elemento a mover debe estar entre 1 y 100 libras";
	public static final String NUMBER_MOVING_ITEMS_PER_WORKDAY_INVALID = "La cantidad de elementos a mover por dia debe estar entre 1 y 100 elementos";
	public static final String NUMBER_WORKDAYS_INVALID = "La cantidad de días a trabajar debe estar entre 1 y 500 días";
	private static final int MINIMUM_WEIGHT_TO_MOVE = 50;
	private static final int MAXIMUM_WORK_DAYS = 500;
	private static final int MINIMUM_WORK_DAYS = 1;
	private static final int MAXIMUM_ITEMS_TO_MOVE_PER_WORKDAY = 100; 
	private static final int MINIMUM_ITEMS_TO_MOVE_PER_WORKDAY = 1;
	private static final int MAXIMUM_ITEM_WEIGHT = 100;
	private static final int MINIMUM_ITEM_WEIGHT = 1;

	
	@Autowired
	TraceRepository traceRepository;

	@Override
	public List<String> makeItemTrips(List<Workday> workdays, String dni) {
		List<String> tripsMadeDay = new ArrayList<>();
		
		validateInput(workdays);
		
		Consumer<Workday> workdayEach = workday -> {			
			int numberOfItems = workday.getItemsToMove().size();
			String tripsPerDay = getTripsPerDay(numberOfItems, workday);
			tripsMadeDay.add(tripsPerDay);
		};
		
		workdays.stream().forEach(workdayEach);
		traceRepository.saveTrace(dni, LocalDateTime.now());
		return tripsMadeDay;
	}
	
	public void validateInput(List<Workday> workdays) {
		validateWorkdays(workdays.size());
		workdays.stream().forEach(workday -> {
			validateItemsToMovePerWorkday(workday.getItemsToMove().size());
			workday.getItemsToMove().stream()
				.forEach(item -> validateItem(item));
			
		});
	}
	
	public void validateWorkdays(int numberWorkdays) {
		if(numberWorkdays > MAXIMUM_WORK_DAYS || numberWorkdays < MINIMUM_WORK_DAYS) {
			throw new OutOfRangeException(NUMBER_WORKDAYS_INVALID);
		}
	}
	
	public void validateItemsToMovePerWorkday(int movingItemsPerWorkday) {
		if(movingItemsPerWorkday > MAXIMUM_ITEMS_TO_MOVE_PER_WORKDAY 
				|| movingItemsPerWorkday < MINIMUM_ITEMS_TO_MOVE_PER_WORKDAY) {
			throw new OutOfRangeException(NUMBER_MOVING_ITEMS_PER_WORKDAY_INVALID);
		}
	}
	
	public void validateItem(Item itemToMove) {
		int weight = itemToMove.getWeight();
		if(weight > MAXIMUM_ITEM_WEIGHT || weight < MINIMUM_ITEM_WEIGHT) {
			throw new OutOfRangeException(ITEM_WEIGHT_INVALID);
		}
	}
	
	public String getTripsPerDay(int numberOfItems, Workday workday) {
		AtomicInteger tripsPerDay = new AtomicInteger(0);
		AtomicInteger movedItems  = new AtomicInteger(0);
		
		Consumer<Item> itemsToMoveEach = item -> {
			if(movedItems.get() < numberOfItems) {
				if(item.getWeight() >= MINIMUM_WEIGHT_TO_MOVE) {
					movedItems.getAndIncrement();
					tripsPerDay.getAndIncrement();
				}else {
					int itemsToMove = getItemsToMove(item.getWeight());
					int remainingItemsToMove = numberOfItems - movedItems.get();
					if(remainingItemsToMove >= itemsToMove) {
						movedItems.addAndGet(itemsToMove);
						tripsPerDay.getAndIncrement();
					}
				}					
			}
		};
		workday.getItemsToMove().stream()
			.sorted((item1, item2) -> item2.getWeight()-item1.getWeight())
			.forEach(itemsToMoveEach);
		
		return String.valueOf(tripsPerDay);
	}
	
	private int getItemsToMove(int weight) {
		return (int) Math.ceil((double)MINIMUM_WEIGHT_TO_MOVE / weight);
	}
	
}
