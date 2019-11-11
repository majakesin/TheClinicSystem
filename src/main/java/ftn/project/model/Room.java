package ftn.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Room")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "HallNumber", nullable = false)
	private String hallNumber;
	
//	@OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
//	private Appointment appointment;
}
