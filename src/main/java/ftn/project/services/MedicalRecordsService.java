package ftn.project.services;

import ftn.project.dto.RecordsDto;

public interface MedicalRecordsService {
	
	RecordsDto getPacientRecords(Long id);
	
	void savePacientRecords(RecordsDto records);
}
