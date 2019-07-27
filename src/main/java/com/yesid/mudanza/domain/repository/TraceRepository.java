package com.yesid.mudanza.domain.repository;

import java.time.LocalDateTime;

public interface TraceRepository {

	public void saveTrace(String dni, LocalDateTime executionDate);
}
