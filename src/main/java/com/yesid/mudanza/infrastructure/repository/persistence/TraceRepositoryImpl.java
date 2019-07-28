package com.yesid.mudanza.infrastructure.repository.persistence;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yesid.mudanza.domain.repository.TraceRepository;
import com.yesid.mudanza.infrastructure.repository.builder.TraceBuilder;
import com.yesid.mudanza.infrastructure.repository.entity.TraceEntity;
import com.yesid.mudanza.infrastructure.repository.persistence.jpa.TraceRepositoryJpa;

@Component
public class TraceRepositoryImpl implements TraceRepository{

	@Autowired
	TraceRepositoryJpa traceRepositoryJpa;
	
	@Override
	public String saveTrace(String dni, LocalDateTime executionDate) {
		TraceEntity traceEntity = traceRepositoryJpa.save(TraceBuilder.convertToTrace(dni, executionDate));
		return traceEntity.getId();
	}

}
