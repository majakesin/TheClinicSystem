package ftn.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	List<Prescription> findAllByPacientIdAndCertified(Long pacientId,boolean certified);
	
	List<Prescription>findAllByCertified(boolean isCertified);
	
}
