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

	public Set<User> findByNameAndSurnameAndInsuranceNumber(String nameDto, String surnameDto,
			String insuranceNumberDto);
	public Set<User> findByNameAndSurname(String nameDto, String surnameDto);
	public Set<User> findByNameAndInsuranceNumber(String nameDto, String insuranceNumberDto);
	public Set<User> findBySurnameAndInsuranceNumber(String nameDto, String insuranceNumberDto);
	public Set<User> findByName(String nameDto);
	public Set<User> findBySurname(String surnameDto);
	public Set<User> findByInsuranceNumber(String insuranceNumberDto);
}
