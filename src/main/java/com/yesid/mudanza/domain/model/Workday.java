package com.yesid.mudanza.domain.model;

import java.util.List;

public class Workday {

	private List<Item> itemsToMove;
	
	public Workday(List<Item> itemsToMove) {
		this.itemsToMove = itemsToMove;
	}

	public List<Item> getItemsToMove() {
		return itemsToMove;
	}
}
