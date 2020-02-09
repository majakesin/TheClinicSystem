package ftn.project.services;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import ftn.project.dto.RecordsDto;
import ftn.project.dto.UserDto;
import ftn.project.model.User;

@Service
public interface UserService extends UserDetailsService,IUserService  {

	void createUser(UserDto userDto);

	
	void deleteUser(Long idDto);
	
	Set<UserDto> allUsers();
	
	Set<UserDto> allUserByRole(String role);

	Set<UserDto> allNurse();
	
	public Set<UserDto> searchDoctorsNepredefinsani(String nameDto, String surnameDto, String markDto,Set<UserDto> doktoriNpd);

	UserDto getUserById(Long idDto);

	UserDto getUserProfile(String username);
	

	UserDto getUserByUsername(String username);
	
	UserDto getUserByRole(String role);
	
	String autentification(UserDto userDto);
	
	RecordsDto getUserRecords(Long userId);
	


	
	Set<UserDto> allMedicalStaff();

	void editUser(Long idDto);

	Set<UserDto> searchPatient(String nameDto, String surnameDto, String insuranceNumberDto);

	Set<UserDto> searchDoctor(String nameDto, String surnameDto, String markDto);
	
	//za autorizaciju korisnika
	void Autorizacija(HttpServletRequest request);

	public boolean getCA();
	public boolean getCCA();
	public boolean getPacijent();
	public boolean getDoktor();
	public boolean getSestra();
	public boolean getNull();
	

}
