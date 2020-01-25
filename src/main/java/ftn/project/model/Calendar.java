package ftn.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Calendar")
public class Calendar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="DoctorId",nullable = true)
	private Long doctorId;
	
	@Column(name="NurseId",nullable = true)
	private Long nurseId;
	
	@Column(name = "Title",nullable=false)
	private String title;
	
	@Column(name="Start",nullable=false)
	private String start;
	
	@Column(name="End",nullable = false)
	private String end;
	
}
