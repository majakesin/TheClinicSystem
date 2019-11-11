package ftn.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
