package com.yesid.mudanza.domain.services;

import java.util.List;

import com.yesid.mudanza.domain.model.Workday;

public interface MakeMoveServices {

	/**
	 * Calcula la manera mas rentable para el transportador de que cantidad 
	 * de elementos debe transportar en cada viaje por cada dia de mudanza.
	 * @return cantidad de viajes realizados por cada dia de mudanza
	 */
	public List<Integer> makeElementTrips(List<Workday> workDays);
}
