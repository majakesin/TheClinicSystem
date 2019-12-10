package ftn.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.sun.jndi.cosnaming.IiopUrl.Address;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Pacient")
public class Pacient {

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

	@Column(name = "Adress", nullable = false)
	private String adress;

	@Column(name = "City", nullable = false)
	private String city;

	@Column(name = "Country", nullable = false)
	private String country;

	@Column(name = "Phone", nullable = false)
	private String phone;

	@Column(name = "Email", nullable = false)
	private String email;

	@Column(name = "InsuranceNumber", nullable = false)
	private String insuranceNumber;
	
	
  
	
	@ManyToOne
	@JoinColumn(name="doctor_id",nullable = true)
	private Doctor doctor;
//	
	@ManyToOne
	@JoinColumn(name="nurse_id",nullable = true)
	private Nurse nurse;
//
	@ManyToOne
	@JoinColumn(name="clinic_id",nullable = false)
	private Clinic clinic;
}
