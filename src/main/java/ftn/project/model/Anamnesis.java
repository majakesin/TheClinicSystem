package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Anamnesis")
public class Anamnesis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="Report",nullable=true)
	private String report;
	
	@Column(name="Diagnosis",nullable=false)
	private Long diagnosisId;
	
	@Column(name="DiagnosisDate",nullable=false)
	private String diagnosisDate;
	
	@Column(name="Prescription",nullable=true)
	private Long prescriptionId;
	
	@Column(name="Pacient",nullable=false)
	private Long pacientId;
	
	
	
}
