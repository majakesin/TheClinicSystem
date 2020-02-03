package ftn.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.model.Clinic;
import ftn.project.model.User;


@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	public Clinic findAllById(Long idDto);
	public Set<Clinic> findByAdressAndMark(String adress, double mark);
	public Set<Clinic> findByAdress(String adress);
	public Set<Clinic> findByMark(double mark);
	
	
}
