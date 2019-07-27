package com.yesid.mudanza.infrastructure.repository.persistence;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.yesid.mudanza.domain.repository.TraceRepository;
import com.yesid.mudanza.infrastructure.repository.builder.TraceBuilder;
import com.yesid.mudanza.infrastructure.repository.persistence.jpa.TraceRepositoryJpa;

public class TraceRepositoryImpl implements TraceRepository{

	@Autowired
	TraceRepositoryJpa traceRepositoryJpa;
	
	@Override
	public void saveTrace(String dni, LocalDateTime executionDate) {
		traceRepositoryJpa.save(TraceBuilder.convertToTrace(dni, executionDate));
	}

}
