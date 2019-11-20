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
@Entity
@Table(name="RegisterRequest")
public class RegisterRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Username", nullable = false)
	private String username;

	@Column(name="Password",nullable = false)
	private String password;
	
	@Column(name = "Name", nullable = true)
	private String name;

	@Column(name = "Surname", nullable = true)
	private String surname;

	@Column(name = "Adress", nullable = true)
	private String adress;

	@Column(name = "City", nullable = true)
	private String city;

	@Column(name = "Country", nullable = true)
	private String country;

	@Column(name = "Phone", nullable = true)
	private String phone;

	@Column(name = "Email", nullable = true)
	private String email;
	
	@Column(name = "Enabled")
	private boolean enabled;
	
	//polje za pacijenta
	@Column(name = "InsuranceNumber", nullable = true)
	private String insuranceNumber;
	
	
	// polja za doktora
	@Column(name= "Biography",nullable = true)
	private String biography;
	
	@Column(name="Mark",nullable = true)
	private String mark;
	
}
