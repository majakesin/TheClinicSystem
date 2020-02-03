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
@Table(name = "Prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Certified", nullable = false)
	private boolean certified;

	@Column(name = "isExaminationFinished", nullable = true)
	private boolean isExamination;
	
	@Column(name = "DrugCode", nullable = false)
	private String drugCode;

	@Column(name = "DrugName", nullable = false)
	private String drugName;
	
	@Column(name = "Describe", nullable = true)
	private String describe;

	@Column(name="Pacient",nullable=true)
	private Long pacientId;
	
	@Column(name="Nurse",nullable=true)
	private Long nurseId;
}
