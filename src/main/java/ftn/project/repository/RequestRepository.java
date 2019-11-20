package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.RegisterRequest;

public interface RequestRepository extends JpaRepository<RegisterRequest, Long> {

}
