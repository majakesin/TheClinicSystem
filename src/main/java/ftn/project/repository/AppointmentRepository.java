package ftn.project.repository;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.model.AppointmentRequest;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentRequest,Long> {

		
}
