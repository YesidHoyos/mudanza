package com.yesid.mudanza.infrastructure.repository.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yesid.mudanza.infrastructure.repository.entity.TraceEntity;

public interface TraceRepositoryJpa extends JpaRepository<TraceEntity, String>{

}
