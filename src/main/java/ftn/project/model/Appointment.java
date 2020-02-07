package ftn.project.model;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data

@Entity
@Table(name = "Appointment")
public class Appointment implements Comparable<Appointment>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Date", nullable = true)
	private String date;
	
	@Column(name = "Time", nullable = true)
	private String time;
	
	@Column(name = "Room", nullable = true)
	private String room;

	@Column(name = "Type", nullable = true)
	private String type;

//	@OneToOne
//    @MapsId
//	private Room room;

	@Column(name = "Doctor", nullable = false)
	private Long doctor;

	@Column(name = "Price", nullable = true)
	private String price;
	
	@Column(name = "Discount", nullable = true)
	private String discount;

	@Column(name = "IsBusy", nullable = true)
	private boolean isBusy;
	
	@Column(name = "IsAccept", nullable = true)
	private boolean isAccept;
	
	@Column(name="Pacijent",nullable=true)
	private Long pacientId;
	
	@ManyToOne
	@JoinColumn(name="appointment_record_id",nullable=true)
	private MedicalRecord record;
	
	@Column(name="Operation",nullable=true)
	private Long operationId;
	
	@ManyToMany
	@JoinTable(name="DoctorsOperations",joinColumns = @JoinColumn(name="operations_id"),inverseJoinColumns=
	@JoinColumn(name="doctor_id"))
	List<User>doctors;

	@Column(name="RoomId",nullable=true)
	private Long roomId;
	
	@Override
	public int compareTo(Appointment o) {
		return (this.getDate().compareTo(o.getDate()));
	}

}
