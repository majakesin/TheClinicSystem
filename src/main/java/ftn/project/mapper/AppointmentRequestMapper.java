package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.AppointmentRequestDto;
import ftn.project.model.AppointmentRequest;
import lombok.Data;

@Component
@Data
public class AppointmentRequestMapper {

	public AppointmentRequestDto appReqToDto(AppointmentRequest appreq) {
		
		AppointmentRequestDto appreqDto = new AppointmentRequestDto();
		
		appreqDto.setIdDto(appreq.getId());
		appreqDto.setRoleDto(appreq.getRole());
		
		return appreqDto;
	}
	
	public AppointmentRequest appReqDtoToAppReq (AppointmentRequestDto appreqDto) {
		
		AppointmentRequest appreq = new AppointmentRequest();
		appreq.setId(appreqDto.getIdDto());
		appreq.setRole(appreq.getRole());
		
		return appreq;
	}
	
	public Set<AppointmentRequest> SetDtoToAppReq(Collection<AppointmentRequestDto> apprDtos) {
		
		Set<AppointmentRequest> apprs  = new HashSet<AppointmentRequest>();// ovako se instancira set

		for (AppointmentRequestDto apprDto : apprDtos) {
			apprs.add(this.appReqDtoToAppReq(apprDto));
		}
		return apprs;
	}
	
public Set<AppointmentRequestDto> SetAppReqToDto(Collection<AppointmentRequest> apprs) {
		
		Set<AppointmentRequestDto> apprDtos  = new HashSet<AppointmentRequestDto>();// ovako se instancira set

		for (AppointmentRequest appr : apprs) {
			apprDtos.add(this.appReqToDto(appr));
		}
		return apprDtos;
	}
}
