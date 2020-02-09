package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.BusyTerms;

public interface BusyTermsRepository extends JpaRepository<BusyTerms, Long> {

}
