package ftn.project.services_impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ftn.project.dto.AnamnesisDto;
import ftn.project.dto.DtoEntity;
import ftn.project.mapper.DtoUtils;
import ftn.project.model.Anamnesis;
import ftn.project.repository.AnamnesisRepository;
import ftn.project.services.AnamnesisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnamenesisServiceImpl implements AnamnesisService{

	private final DtoUtils dtoUtils;
	
	private final AnamnesisRepository anamnesisRepository;
	
	@Override
	public Set<DtoEntity> getAllAnamnesis() {
		List<Anamnesis> anamnesList=anamnesisRepository.findAll();
		return anamnesList.stream().map(a->dtoUtils.convertToDto(a, new AnamnesisDto()))
				.collect(Collectors.toSet());
	}

	@Override
	public DtoEntity getById(Long anamId) {
		// TODO Auto-generated method stub
		return dtoUtils.convertToDto(anamnesisRepository.findById(anamId),new AnamnesisDto());
	}

	@Override
	public void save(DtoEntity anamnesisDto) {
		Anamnesis anamnesis=(Anamnesis) dtoUtils.convertToEntity(new Anamnesis(), anamnesisDto);
		anamnesisRepository.save(anamnesis);
		
	}

	@Override
	public Set<DtoEntity> getAllAnamnesisByPacientId(Long pacient) {
		List<Anamnesis>anamnesisList=anamnesisRepository.findAllByPacientId(pacient);
		return anamnesisList.stream().map(an->dtoUtils.convertToDto(an, new AnamnesisDto())).collect(Collectors.toSet());
	}



}
