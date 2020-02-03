package ftn.project.dto;

import lombok.Data;

@Data
public class AnamnesisDto implements DtoEntity {

	private Long id;
	private String report;
	private Long diagnosisId;
	private String diagnosisDate;
	private Long prescription;
	private Long pacientId;
}
