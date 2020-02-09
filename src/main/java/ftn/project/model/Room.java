package ftn.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Room")
public class Room {

	public Room() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Name", nullable = false)
	private String name;

	@Column(name = "HallNumber", nullable = false)
	private String hallNumber;
	
	@Column(name="Free",nullable=false)
	private boolean free;
	
	@ManyToMany
	@JoinTable(name="BusyTermsOfRooms",joinColumns = @JoinColumn(name="room_id"),
	inverseJoinColumns = @JoinColumn(name="busy_id"))
	List<BusyTerms> terms=new ArrayList<BusyTerms>();

}
