package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Certified", nullable = false)
	private boolean certified;

	@Column(name = "DrugCode", nullable = false)
	private String drugCode;

	@Column(name = "DrugName", nullable = false)
	private String drugName;

}
