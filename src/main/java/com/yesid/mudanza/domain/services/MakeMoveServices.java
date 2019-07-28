package com.yesid.mudanza.domain.services;

import java.util.List;

import com.yesid.mudanza.domain.model.Workday;

public interface MakeMoveServices {

	/**
	 * Calcula la manera mas rentable para el transportador de que cantidad 
	 * de elementos debe transportar en cada viaje por cada dia de mudanza.
	 * 
	 * @param workDays: días de trabajo con los elementos que hay que mover por día
	 * @param dni: identificación del transportista
	 * @return cantidad de viajes realizados por cada día de mudanza
	 */
	public List<String> makeItemTrips(List<Workday> workDays, String dni);
}
