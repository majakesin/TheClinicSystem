package ftn.project.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Nurse")
public class Nurse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Username", nullable = false)
	private String username;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Surname", nullable = false)
	private String surname;

	@ManyToOne
	@JoinColumn(name="clinic_id",nullable = false)
	private Clinic clinic;
	
	@OneToMany(mappedBy = "nurse")
	private Set<Pacient> pacients;

	@Column(name="Prescition", nullable = false)
	private String prescriptionId;
	
}
