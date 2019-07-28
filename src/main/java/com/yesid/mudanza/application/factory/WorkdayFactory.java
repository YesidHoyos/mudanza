package com.yesid.mudanza.application.factory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yesid.mudanza.application.exception.UnexpectedException;
import com.yesid.mudanza.domain.model.Item;
import com.yesid.mudanza.domain.model.Workday;

@Component
public class WorkdayFactory {

	public static final String UNEXPECTED_EXCEPTION = "Ha ocurrido un error inesperado";

	public List<Workday> create(MultipartFile multipartFile) {
		BufferedReader assignedTasks = multipartFileToBufferedReader(multipartFile);
		List<Item> itemsToMove = new ArrayList<>();
		List<Workday> workDays = new ArrayList<>();
		AtomicInteger dayLinePosition = new AtomicInteger(0);
		AtomicInteger linePosition = new AtomicInteger(1);

		IntConsumer lineaEach = line -> {
			if(linePosition.get() > dayLinePosition.get()) {
				dayLinePosition.addAndGet(line);
				dayLinePosition.getAndIncrement();
			} else if(linePosition.get() < dayLinePosition.get()){
				itemsToMove.add(new Item(line));
			} else {
				itemsToMove.add(new Item(line));
				workDays.add(new Workday(new ArrayList<>(itemsToMove)));
				itemsToMove.clear();
			}
			linePosition.incrementAndGet();
		};
		assignedTasks.lines().mapToInt(Integer::parseInt).skip(1).forEach(lineaEach);	
		return workDays;
	}
	
	private BufferedReader multipartFileToBufferedReader(MultipartFile multipartFile) { 
		InputStream inputStream = null;
		try {
			inputStream = multipartFile.getInputStream();
		} catch (IOException e) {
			throw new UnexpectedException(UNEXPECTED_EXCEPTION);
		}		 
	    return  new BufferedReader(new InputStreamReader(inputStream));
	}
}
