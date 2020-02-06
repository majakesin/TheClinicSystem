package ftn.project.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Appointment;

public interface AppoitmentRepository extends JpaRepository<Appointment, Long> {

	public List<Appointment> findAllByIsBusy(boolean isBusy);
	
	public List<Appointment> findAllByisAccept(boolean isAccept);
	
	List<Appointment>findAllByDoctor(Long doctor);
	
	List<Appointment>findAllByPacientId(Long pacient);

	List<Appointment>findAllByDoctors(Long doctor);
	
	List<Appointment>findAllByRoomId(Long id);

	public Set<Appointment> findByDateAndType(String date, String type);
	public Set<Appointment> findByDate(String date);
	public Set<Appointment> findByType(String type);

}
