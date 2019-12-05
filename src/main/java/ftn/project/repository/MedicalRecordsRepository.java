package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.MedicalRecord;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecord, Long> {
	

	
}
