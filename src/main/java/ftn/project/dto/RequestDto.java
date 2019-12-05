package ftn.project.dto;

import ftn.project.model.MedicalRecord;
import lombok.Data;

@Data
public class RequestDto {
	
	public Long idDto;
	public String usernameDto;
	public String passwordDto;
	public String nameDto;
	public String surnameDto;
	public String addressDto;
	public String cityDto;
	public String phoneDto;
	public String emailDto;
	public String roleDto;
	
	private MedicalRecord medicalDto;
	
	private String countryDto;
	private String insuranceNumberDto;
	

	//polja za doktora
	private String biographyDto;
	private String markDto;
}
