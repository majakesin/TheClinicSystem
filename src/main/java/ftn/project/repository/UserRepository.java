package ftn.project.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public Set<User> findAllByRole(String s); 

	public User findByUsername(String username);
}
