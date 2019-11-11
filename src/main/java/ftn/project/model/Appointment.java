package ftn.project.model;

import java.sql.Date;

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
@AllArgsConstructor
@Entity
@Table(name = "Appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DateAndTime", nullable = false)
	private Date dateAndTime;

	@Column(name = "Type", nullable = false)
	private Type type;

//	@OneToOne
//    @MapsId
//	private Room room;

	@ManyToOne
	@JoinColumn(name="appointment_id",nullable=false)
	private Doctor doctor;

	@Column(name = "Price", nullable = false)
	private String price;

	@Column(name = "IsBusy", nullable = false)
	private boolean isBusy;
	
	@ManyToOne
	@JoinColumn(name="appointment_record_id",nullable=false)
	private MedicalRecord record;

}
