package ftn.project.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "Clinic")
public class Clinic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name", nullable = true)
	private String name;

	@Column(name = "Adress", nullable = true)
	private String adress;

	@Column(name = "Phone", nullable = true)
	private String phone;

	@Column(name = "Description", nullable = true)
	private String description;

//	@OneToOne
//    @MapsId
//	private PriceList priceList;

	@Column(name = "Mark", nullable = true)
	private double mark;

	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
	private Set<Doctor> doctors;

	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
	private Set<Nurse> nurse;

	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
	private Set<ClinicAdministrator> clinicAdministrators;
	
	@OneToMany(mappedBy = "clinic",cascade = CascadeType.ALL)
	private Set<Pacient> pacients;

//	@ManyToMany
//	@JoinTable(name = "ClinicAdminRelations", 
//		joinColumns = @JoinColumn(name = "clinic_id",nullable=true), 
//		inverseJoinColumns = @JoinColumn(name = "admin_id",nullable=true))
//	Set<ClinicCenterAdministrator> administrators;
}
