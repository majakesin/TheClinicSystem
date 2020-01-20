package ftn.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Appointment;

public interface SchedulingRequestRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findAllByIsBusy(boolean isBusy);
	
	public List<Appointment> findAllByisAccept(boolean isAccept);
	
	List<Appointment>findAllByDoctor(Long doctor);
}
