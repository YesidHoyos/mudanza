package com.yesid.mudanza.infrastructure.repository.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Table(name = "trace")
@Entity
@NoArgsConstructor
public class TraceEntity {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid2")
	private String id;
	private String executorDni;
	private LocalDateTime executionDate;

}
