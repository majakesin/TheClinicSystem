package ftn.project.mapper;

import org.springframework.stereotype.Component;

import ftn.project.dto.RecordsDto;
import ftn.project.model.MedicalRecord;
import lombok.Data;

@Component
@Data
public class MedicalRecordsMapper {
	
	public MedicalRecord dtoToRecords(RecordsDto recordsDto) {
		MedicalRecord medicalRecords=new MedicalRecord();
		medicalRecords.setAllergy(recordsDto.getAllergy());
		medicalRecords.setBloodType(recordsDto.getBloodType());
		medicalRecords.setHeight(recordsDto.getHeightDto());
		medicalRecords.setId(recordsDto.getIdDto());
		medicalRecords.setWeight(recordsDto.getWeightDto());
		return medicalRecords;
	}
	
	public RecordsDto recordsToDto(MedicalRecord medicalRecords) {
		RecordsDto records=new RecordsDto();
		records.setAllergy(medicalRecords.getAllergy());
		records.setBloodType(medicalRecords.getBloodType());
		records.setHeightDto(medicalRecords.getHeight());
		records.setWeightDto(medicalRecords.getWeight());
		records.setIdDto(medicalRecords.getId());
		return records;
	}
}
