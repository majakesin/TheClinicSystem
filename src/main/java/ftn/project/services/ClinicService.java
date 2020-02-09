package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;
import ftn.project.model.User;


@Service
public interface ClinicService {
	
	public void createClinic(ClinicDto clinicDto);

	public void editClinicProfile(ClinicDto clinicDto);
	
	public void deleteClinic(Long id);
	
	public Set<ClinicDto> allClinics();

	public ClinicDto getClinicById(Long id);

	public ClinicDto getClinicProfile(String ussername);
	
	//pretraga klinike
	public String getAdresa();
	
	public String getTip();
	
	public String getDatum();
	
	public Double getOcena();
	
	Set<ClinicDto> searchClinic(String adressDto, double markDto );

	Set<ClinicDto> searchClinicByDoctor(String dateDto, String typeDto);
	
	//pretrage datuma klinika
	UserDto getDoktoreSlobodne(String datum,User user);
	UserDto getDoktoreSlobodneTP(String datum,String tip,User user);
	UserDto getDoktoreTP(String tip,User user);
	
	//za zakazivanje nepredefinisanih
	public Set<UserDto> vratiDoktori1 ();
	public Set<UserDto> vratiDoktori2 ();
}
