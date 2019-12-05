package ftn.project.repository;





import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
