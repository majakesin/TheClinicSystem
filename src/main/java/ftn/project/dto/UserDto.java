package ftn.project.dto;

import lombok.Data;

@Data
public class UserDto {

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
	private String countryDto;
	private String insuranceNumberDto;
	private String medicalRecordIdDto;
	
	//menjanjesifre
	private Boolean prviLoginDto;
	private String pomocnaSifraDto;

	//polja za doktora
	private String biographyDto;
	private String markDto;
	
	//polje za select klinike
	private Long clinicDto;
}
