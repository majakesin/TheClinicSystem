package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.CodeBook;

public interface CodeBookRepository extends JpaRepository<CodeBook, Long> {

}
