package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.model.Clinic;


@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {

	public Clinic findAllById(Long idDto);
}
