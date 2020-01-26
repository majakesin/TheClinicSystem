package ftn.project.dto;



import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RecordsDto {
	private Long idDto;

	private double heightDto;
	private double weightDto;
	
	private String bloodType;
	private String allergy;
}
