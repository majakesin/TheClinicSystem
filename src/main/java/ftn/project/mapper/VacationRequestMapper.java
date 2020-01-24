package ftn.project.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.VacationRequestDto;
import ftn.project.model.VacationRequest;
import lombok.Data;

@Component
@Data
public class VacationRequestMapper {

	
	public VacationRequest DtoToModel( VacationRequestDto vqDto) {
		
		VacationRequest retVal= new VacationRequest();
		
		retVal.setTekstMaila(vqDto.getTekstMailaDto());
		retVal.setSubjekatMaila(vqDto.getSubjekatMailaDto());
		retVal.setId(vqDto.getIdDto());
		retVal.setName(vqDto.getNameDto());
		retVal.setKrajGodisnjeg(vqDto.getKrajGodisnjegDto());
		retVal.setPocetakGodisnjeg(vqDto.getPocetakGodisnjegDto());
		retVal.setSurname(vqDto.getSurnameDto());
		retVal.setUsername(vqDto.getUsernameDto());
		retVal.setRole(vqDto.getRoleDto());
		retVal.setEmail(vqDto.getEmailDto());
		return retVal;
	}
	
	public VacationRequestDto ModelToDto( VacationRequest vq) {
		
		VacationRequestDto retValDto = new VacationRequestDto();
		
		retValDto.setTekstMailaDto(vq.getTekstMaila());
		retValDto.setSubjekatMailaDto(vq.getSubjekatMaila());
		retValDto.setEmailDto(vq.getEmail());
		retValDto.setNameDto(vq.getName());
		retValDto.setIdDto(vq.getId());
		retValDto.setPocetakGodisnjegDto(vq.getPocetakGodisnjeg());
		retValDto.setKrajGodisnjegDto(vq.getKrajGodisnjeg());
		retValDto.setSurnameDto(vq.getSurname());
		retValDto.setUsernameDto(vq.getUsername());
		retValDto.setRoleDto(vq.getRole());

		return retValDto;
	}
	
	
	public Set<VacationRequest> DtoToModelSet(Set<VacationRequestDto> vqD) {
	
		Set<VacationRequest> vqs = new HashSet<VacationRequest> ();
		
		for( VacationRequestDto vqd: vqD) {
			
			vqs.add(this.DtoToModel(vqd));
		}
		
		return vqs;
	}
	
	public Set<VacationRequestDto> ModelToDtoSet(List<VacationRequest> list) {
		
		Set<VacationRequestDto> vqD = new HashSet<VacationRequestDto> ();
		
		for( VacationRequest vq: list) {
			
			vqD.add(this.ModelToDto(vq));
		}
		
		return vqD;
	}
	
}
