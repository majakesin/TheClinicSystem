package ftn.project.dto;

import javax.persistence.Column;

import ftn.project.model.Role;
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
	

	//polja za doktora
	private String biographyDto;
	private String markDto;
}
