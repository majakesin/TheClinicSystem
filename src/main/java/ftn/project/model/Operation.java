package ftn.project.model;

import java.sql.Date;

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
@Table(name = "Operation")
public class Operation {

//	private Doctor doctor;
//	private Pacient pacinet;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "StartTime", nullable = true)
	private Date startTime;

	@Column(name = "Duration", nullable = true)
	private Double duration;

	@Column(name = "HallNumber", nullable = true)
	private String hallNumber;

//	@OneToOne(mappedBy = "operation", cascade = CascadeType.ALL)
//	private PriceList priceList;
}
