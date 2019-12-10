package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;


import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "Codebook")
public class CodeBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DiagnosisCode", nullable = false)
	private String diagnosisCode;

	@Column(name = "DiagnosisName", nullable = false)
	private String diagnosisName;

	@Column(name = "DrugCode", nullable = false)
	private String drugCode;

	@Column(name = "DrugName", nullable = false)
	private String drugName;

	@Column(name = "DiagnosisDescription", nullable = false)
	private String diagnosisDescription;

	@Column(name = "DrugDescription", nullable = true)
	private String drugDescription;

	@Column(name = "CodeDiagnose", nullable = true)
	private String medicCode;

}
