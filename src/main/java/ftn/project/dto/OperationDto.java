package ftn.project.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class OperationDto {
	private ArrayList<Long> operationIds;
	private Long termId;
	private Long roomDto;
	
	
}
