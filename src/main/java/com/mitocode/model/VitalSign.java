package com.mitocode.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class VitalSign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer idVitalSign;

	@ManyToOne
	@JoinColumn(name = "id_patient", foreignKey = @ForeignKey(name = "FK_VITAL_SIGN_PATIENT"))
	private Patient idPatient;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false) 
	private double temperature;

	@Column(nullable = false)
	private int pulse;

	@Column( nullable = false) 
	private int respiratoryRate;
}
