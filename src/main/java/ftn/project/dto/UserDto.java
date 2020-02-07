package ftn.project.dto;

import java.util.HashSet;
import java.util.Set;

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
	private String tipPregledaDto;
	private String pocetakGodisnjegDto;
	private String krajGodisnjegDto;
	private String vremePregledaDto;
	
	//menjanjesifre
	private Boolean prviLoginDto;
	private String pomocnaSifraDto;

	//polja za doktora
	private String biographyDto;
	private String markDto;
	
	
	//polje za select klinike
	private Long clinicDto;
}
