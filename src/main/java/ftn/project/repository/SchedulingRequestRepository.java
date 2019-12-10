package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.dto.AppointmentDto;
import ftn.project.model.Appointment;

public interface SchedulingRequestRepository extends JpaRepository<Appointment, Long> {

	public Appointment findAllById(Long idDto);
}
