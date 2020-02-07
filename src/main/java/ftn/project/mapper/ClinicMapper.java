package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.ClinicDto;
import ftn.project.model.Clinic;
import lombok.Data;

@Component
@Data
public class ClinicMapper {

	private UserMapper userMapper;
	
	public ClinicDto ClinicToDto(Clinic clinic) {

		ClinicDto clinicDto = new ClinicDto();

		clinicDto.setIdDto(clinic.getId());

		clinicDto.setNameDto(clinic.getName());
		clinicDto.setAdressDto(clinic.getAdress());
		clinicDto.setPhoneDto(clinic.getPhone());

		clinicDto.setDescriptionDto(clinic.getDescription());
		clinicDto.setMarkDto(clinic.getMark());
		
		return clinicDto;
		
	}

	public Clinic DtoToClinic(ClinicDto clinicDto) {

		Clinic clinic = new Clinic();
		clinic.setId(clinicDto.getIdDto());

		clinic.setName(clinicDto.getNameDto());
		clinic.setAdress(clinicDto.getAdressDto());
		clinic.setPhone(clinicDto.getPhoneDto());

		clinic.setDescription(clinicDto.getDescriptionDto());
		clinic.setMark(clinicDto.getMarkDto());
		
		return clinic;

	}

	/*
	 * Koristio sam Collection da bi mogao da posaljem objekat set jer svi oni
	 * nasledjuju Collection, inace ne bi mogao da vratim set
	 */
	public Set<Clinic> DtoToClinicSet(Collection<ClinicDto> clinicsDto) {

		Set<Clinic> clinics = new HashSet<Clinic>();// ovako se instancira set

		for (ClinicDto clinicDto : clinicsDto) {
			clinics.add(this.DtoToClinic(clinicDto));
		}
		return clinics;
	}

	public Set<ClinicDto> ClinicToDtoSet(Collection<Clinic> clinics) {

		Set<ClinicDto> clinicsDto = new HashSet<ClinicDto>();
		for (Clinic clinic : clinics) {
			clinicsDto.add(this.ClinicToDto(clinic));
		}
		return clinicsDto;

	}

}
