package ftn.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Anamnesis;

public interface AnamnesisRepository extends JpaRepository<Anamnesis, Long> {

	Anamnesis findByPacientId(Long pacientId);
	
	List<Anamnesis> findAllByPacientId(Long pacientId);
	
}
