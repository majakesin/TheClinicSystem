package ftn.project.services;

import ftn.project.dto.OperationDto;

public interface OperationService {

	void scheduleOperation();
	
	void createOperation(OperationDto operationDto,boolean isOperation);
	
	void automaticSystem();
	
	Long fullRooms(Long id);
	
	void changeOperation(Long idTerm,String time,String date,Long room);
	
	void createExamination(Long idTerm,String time,String date,Long room);
	
}
