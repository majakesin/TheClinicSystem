package ftn.project.services;

import java.util.Set;

import ftn.project.dto.DtoEntity;

public interface AnamnesisService {

	Set<DtoEntity> getAllAnamnesis();
	
	Set<DtoEntity> getAllAnamnesisByPacientId(Long pacient);

	DtoEntity getById(Long anamId);
	
	void save(DtoEntity anamnesis);

}
