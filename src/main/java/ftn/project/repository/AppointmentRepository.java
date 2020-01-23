package ftn.project.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.model.Appointment;
import ftn.project.model.AppointmentRequest;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentRequest,Long> {

	
		
}
