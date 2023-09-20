package com.mitocode.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VitalSignDTO {

	@EqualsAndHashCode.Include
	private Integer idVitalSign;

	@NotNull
	private PatientDTO patient;

	@NotNull
	private LocalDate date;

	@NotNull
	private double temperature;
	
	@NotNull
	private int pulse;
	
	@NotNull
	private int respiratoryRate;
}
