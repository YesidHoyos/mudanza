package com.yesid.mudanza.domain.model;

import java.util.List;

public class Workday {

	private List<MovingElement> itemsToTransport;
	
	public Workday(List<MovingElement> itemsToTransport) {
		this.itemsToTransport = itemsToTransport;
	}

	public List<MovingElement> getItemsToTransport() {
		return itemsToTransport;
	}
}
