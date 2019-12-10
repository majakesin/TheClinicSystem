package ftn.project.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

@Entity
@Table(name = "Appointment")
public class Appointment {

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

	@ManyToOne
	@JoinColumn(name="appointment_id",nullable=true)
	private Doctor doctor;

	@Column(name = "Price", nullable = true)
	private String price;
	
	@Column(name = "Discount", nullable = true)
	private String discount;

	@Column(name = "IsBusy", nullable = true)
	private boolean isBusy;
	
	@ManyToOne
	@JoinColumn(name="appointment_record_id",nullable=true)
	private MedicalRecord record;

}
