package ftn.project.services_impl;

import org.springframework.stereotype.Service;

import ftn.project.dto.RecordsDto;
import ftn.project.mapper.MedicalRecordsMapper;
import ftn.project.model.MedicalRecord;
import ftn.project.repository.MedicalRecordsRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.MedicalRecordsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicalRecordsServiceImpl implements MedicalRecordsService {

	private final MedicalRecordsRepository medicalRecordsRepository;

	private final MedicalRecordsMapper medicalRecordMapper;
	
	private final UserRepository userRepository;

	@Override
	public RecordsDto getPacientRecords(Long id) {
		MedicalRecord record = userRepository.findById(id).get().getMedicalRecord();
		return medicalRecordMapper.recordsToDto(record);
	}

	@Override
	public void savePacientRecords(RecordsDto recordsDto) {
		MedicalRecord record = medicalRecordMapper.dtoToRecords(recordsDto);
		medicalRecordsRepository.save(record);
	}

}
