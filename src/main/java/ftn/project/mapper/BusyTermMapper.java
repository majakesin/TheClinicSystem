package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import org.springframework.stereotype.Component;

import ftn.project.dto.BusyTermDto;
import ftn.project.model.BusyTerms;
import lombok.Data;

@Component
@Data
public class BusyTermMapper {
	
	public BusyTermDto modelToDto(BusyTerms busyTerms) {
		BusyTermDto busyTermDto=new BusyTermDto();
		busyTermDto.setDateDto(busyTerms.getDate());
		busyTermDto.setIdDto(busyTerms.getId());
		busyTermDto.setTimeDto(busyTerms.getTime());
		return busyTermDto;
	}
	
	public BusyTerms dtoToModel(BusyTermDto busyTermDto) {
		BusyTerms busyTerm=new BusyTerms();
		busyTerm.setDate(busyTermDto.getDateDto());
		busyTerm.setId(busyTermDto.getIdDto());
		busyTerm.setTime(busyTermDto.getTimeDto());
		return busyTerm;
	}
	
	public Set<BusyTermDto> modelToDtoSet(Collection<BusyTerms> terms){
		Set<BusyTermDto>setTerms=new HashSet<BusyTermDto>();
		for(BusyTerms b:terms) {
			setTerms.add(this.modelToDto(b));
		}
		return setTerms;
	}
	
	public Set<BusyTerms> dtoToModelSet(Collection<BusyTermDto>termsDto){
		Set<BusyTerms>setTerms=new HashSet<BusyTerms>();
		for(BusyTermDto bdto:termsDto) {
			setTerms.add(this.dtoToModel(bdto));
		}
		return setTerms;
	}
	
}
