package ftn.project.dto;

import lombok.Data;

@Data
public class PrescriptionDto implements DtoEntity {
	private Long id;
	private String drugCode;
	private String drugName;
	private String describe;
	private boolean certified;
	private boolean isExamination;
	private Long pacientId;
	private Long nurseId;
}
