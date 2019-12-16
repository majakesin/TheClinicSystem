package ftn.project.services_impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.mapper.ClinicMapper;
import ftn.project.model.Clinic;
import ftn.project.model.User;
import ftn.project.repository.ClinicRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.ClinicService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;
	
	@Autowired
	private ClinicMapper clinicMapper;
	
	private final UserRepository userRepository;

	
	public void deleteClinic(Long id) {
		clinicRepository.deleteById(id);
	}

	public void createClinic(ClinicDto clinicDto) {
		User user=userRepository.findByUsername(clinicDto.getAdminDto());
		Clinic clinic=clinicMapper.DtoToClinic(clinicDto);
		user.setClinic(clinic);
		userRepository.save(user);
		
	}
	
	public Set<ClinicDto> allClinics() {
		return clinicMapper.ClinicToDtoSet(clinicRepository.findAll());
	}

	@Override
	public ClinicDto getClinicById(Long id) {
		// TODO Auto-generated method stub
		return clinicMapper.ClinicToDto(clinicRepository.findAllById(id));
	}

	@Override
	public ClinicDto getClinicProfile(String ussername) {
		// TODO Auto-generated method stub
		
		return clinicMapper.ClinicToDto(userRepository.findByUsername(ussername).getClinic());
	}

	@Override
	public void editClinicProfile(ClinicDto clinicDto) {
		clinicRepository.save(clinicMapper.DtoToClinic(clinicDto));
		
	}

	

}
