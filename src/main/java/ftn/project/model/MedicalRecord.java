package ftn.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "MedicalRecord")
public class MedicalRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Height", nullable = false)
	private double height;

	@Column(name = "Weight", nullable = false)
	private double weight;

	@Column(name = "BloodType", nullable = false)
	private String bloodType;

	@Column(name = "Allergy", nullable = false)
	private String allergy;

	@OneToMany(mappedBy = "record")
	private Set<Appointment> appointments;

}
