package ftn.project.services_impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.ClinicDto;
import ftn.project.mapper.ClinicMapper;
import ftn.project.repository.ClinicRepository;
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

	
	public void deleteClinic(Long id) {
		clinicRepository.deleteById(id);
	}

	public void createClinic(ClinicDto clinicDto) {
		clinicRepository.save(clinicMapper.DtoToClinic(clinicDto));
	}
	
	public Set<ClinicDto> allClinics() {
		return clinicMapper.ClinicToDtoSet(clinicRepository.findAll());
	}

}
