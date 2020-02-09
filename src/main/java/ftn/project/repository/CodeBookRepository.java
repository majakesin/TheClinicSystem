package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.CodeBook;
import ftn.project.model.User;

public interface CodeBookRepository extends JpaRepository<CodeBook, Long> {

	
	public CodeBook findByDiagnosisCode(String diag);
	public CodeBook findByDrugCode(String drug);
	public CodeBook findByMedicCode(String medic);
}
