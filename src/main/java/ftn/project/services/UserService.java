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
	

	Set<UserDto> allNurse();
	
<<<<<<< HEAD
	 UserDto getUserById(Long idDto);

=======
	UserDto getUserById(Long idDto);
	
>>>>>>> 11aefb9ab9caf85e1c3c8a55f8300d83c9e818a5
	UserDto getUserById(String username);
	
	UserDto getUserByRole(String role);
	
	String autentification(UserDto userDto);


	
	Set<UserDto> allMedicalStaff();

	void editUser(Long idDto);

	

}
