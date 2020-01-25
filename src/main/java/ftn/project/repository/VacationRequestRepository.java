package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftn.project.model.VacationRequest;

@Repository
public interface VacationRequestRepository extends JpaRepository<VacationRequest,Long> {

}
