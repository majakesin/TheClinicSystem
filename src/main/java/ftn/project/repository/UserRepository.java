package ftn.project.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.project.dto.UserDto;
import ftn.project.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByRole(String role);
  
  
  
  
	public Set<User> findAllByRole(String s); 


//	public List<User> findAllByAuthority(String s); 
	User findByUsername(String username);

}
