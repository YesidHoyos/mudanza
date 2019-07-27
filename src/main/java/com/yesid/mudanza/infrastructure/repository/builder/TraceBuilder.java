package com.yesid.mudanza.infrastructure.repository.builder;

import java.time.LocalDateTime;

import com.yesid.mudanza.infrastructure.repository.entity.TraceEntity;

public class TraceBuilder {

	public static TraceEntity convertToTrace(String dni, LocalDateTime executionDate) {
		TraceEntity traceEntity =  new TraceEntity();
		traceEntity.setExecutorDni(dni);
		traceEntity.setExecutionDate(executionDate);
		return traceEntity;
	}
}
