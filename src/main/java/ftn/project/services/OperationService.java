package ftn.project.services;

import ftn.project.dto.OperationDto;

public interface OperationService {

	void scheduleOperation();
	
	void createOperation(OperationDto operationDto);
	
	Long fullRooms(Long id);
	
}
