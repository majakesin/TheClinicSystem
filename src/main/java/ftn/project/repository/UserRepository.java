package ftn.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.model.MedicalRecord;
import ftn.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {


	
	public Set<User> findAllByRole(String s); 
	public User findAllById(Long idDto);
	


	User findByRole(String role); 


//	public List<User> findAllByAuthority(String s); 
	User findByUsername(String username);

}
