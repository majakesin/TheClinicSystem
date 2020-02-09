package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
