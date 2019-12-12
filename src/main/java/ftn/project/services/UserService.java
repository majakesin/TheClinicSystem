package ftn.project.services;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ftn.project.dto.UserDto;
import ftn.project.model.User;

@Service
public interface UserService extends UserDetailsService,IUserService  {

	void createUser(UserDto userDto);

	void deleteUser(Long idDto);
	
	Set<UserDto> allUsers();
	
	Set<UserDto> allUserByRole(String role);

	Set<UserDto> allNurse();
	

	UserDto getUserById(Long idDto);


	

	UserDto getUserByUsername(String username);
	
	UserDto getUserByRole(String role);
	
	String autentification(UserDto userDto);
	
	


	
	Set<UserDto> allMedicalStaff();

	void editUser(Long idDto);

	Set<UserDto> searchPatient(String nameDto, String surnameDto, String insuranceNumberDto);

	

}
