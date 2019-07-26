package com.yesid.mudanza.domain.model;

import java.util.List;

public class Carrier {

	private List<Workday> workdays;
	
	public Carrier(List<Workday> workdays) {
		this.workdays = workdays;
		
	}

	public List<Workday> getWorkdays() {
		return workdays;
	}
}
