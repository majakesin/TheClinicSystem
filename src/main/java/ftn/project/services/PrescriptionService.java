package ftn.project.services;

import java.util.Set;

import ftn.project.dto.DtoEntity;

public interface PrescriptionService {

	Set<DtoEntity> getAllPrescriptions();
	
	Set<DtoEntity> getAllPrescriptionsByPacientId(Long id);
	
	void certifiedPrescription(Long idPrescription);
	
	void createPrescription(DtoEntity dto);
	
	void finishedExamination(Long pacientId);
	
	void deletePrescription(Long id);
	
	Set<DtoEntity> getAllPrescriptionsByCertified(boolean certified);
}
