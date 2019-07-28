package com.yesid.mudanza.domain.repository;

import java.time.LocalDateTime;

public interface TraceRepository {

	public String saveTrace(String dni, LocalDateTime executionDate);
}
