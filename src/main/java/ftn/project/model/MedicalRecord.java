package ftn.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@JsonIgnoreType
@RequiredArgsConstructor
@Entity
@Table(name = "MedicalRecord")
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Height", nullable = true)
	private double height;

	@Column(name = "Weight", nullable = true)
	private double weight;

	@Column(name = "BloodType", nullable = true)
	private String bloodType;

	@Column(name = "Allergy", nullable = true)
	private String allergy;
	
	
	@OneToMany(mappedBy = "record")
	private Set<Appointment> appointments;

}
