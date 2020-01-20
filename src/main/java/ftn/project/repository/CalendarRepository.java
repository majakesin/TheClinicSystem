package ftn.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	List<Calendar> findAllByDoctorId(Long doctorId);
	List<Calendar> findAllByNurseId(Long nurseId);
	
}
