package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="VacationRequest")
public class VacationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="Username",nullable = false)
	private String username;
	
	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "Surname", nullable = false)
	private String surname;
	
	@Column(name = "Role", nullable = false)
	private String role;
	
	@Column(name="Absence",nullable = false)
	private Absence absence;
	
	@Column(name="Description",nullable = false)
	private String description;	
	
}
