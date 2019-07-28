package com.yesid.mudanza.domain.model;

import java.util.List;

public class Workday {

	private List<MovingElement> elementsToMove;
	
	public Workday(List<MovingElement> elementsToMove) {
		this.elementsToMove = elementsToMove;
	}

	public List<MovingElement> getElementsToMove() {
		return elementsToMove;
	}
}
