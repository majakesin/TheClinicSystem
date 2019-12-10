package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.dto.UserDto;


@Service
public interface ClinicService {
	
	public void createClinic(ClinicDto clinicDto);

	public void deleteClinic(Long id);
	
	public Set<ClinicDto> allClinics();

	public ClinicDto getClinicById(Long id);

	
	
	
}
