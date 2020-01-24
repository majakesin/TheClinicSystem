package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity()
@Table(name="VacationRequest")
public class VacationRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="Username",nullable = true)
	private String username;
	
	@Column(name = "Name", nullable = true)
	private String name;

	@Column(name = "Surname", nullable = true)
	private String surname;
	
	@Column(name = "Role", nullable = true)
	private String role;
	
	@Column(name = "PocetakGodisnjeg", nullable = true)
	private String pocetakGodisnjeg;
	

	@Column(name = "KrajGodisnjeg", nullable = true)
	private String krajGodisnjeg;
	
	@Column(name = "Email", nullable = true)
	private String email;

	@Column(name = "TekstMaila", nullable = true)
	private String tekstMaila;
	
	@Column(name = "SubjekatMaila", nullable = true)
	private String subjekatMaila;
}
