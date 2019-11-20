package ftn.project.mapper;



import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.RequestDto;
import ftn.project.dto.UserDto;
import ftn.project.model.RegisterRequest;
import ftn.project.model.User;
import lombok.Data;

@Data
@Component
public class RequestMapper {
	
	public RegisterRequest dtoToRequest(RequestDto requestDto) {
		RegisterRequest registerRequest = new RegisterRequest();

		registerRequest.setId(requestDto.getIdDto());

		registerRequest.setUsername(requestDto.getUsernameDto());
		registerRequest.setPassword(requestDto.getPasswordDto());
		registerRequest.setEmail(requestDto.getEmailDto());

		registerRequest.setName(requestDto.getNameDto());
		registerRequest.setSurname(requestDto.getSurnameDto());
		registerRequest.setAdress(requestDto.getAddressDto());
		registerRequest.setCity(requestDto.getCityDto());
		registerRequest.setPhone(requestDto.getPhoneDto());
		
		registerRequest.setBiography(requestDto.getBiographyDto());
		registerRequest.setMark(requestDto.getMarkDto());
		
		registerRequest.setCountry(requestDto.getCountryDto());
		registerRequest.setInsuranceNumber(requestDto.getInsuranceNumberDto());
		
		
		return registerRequest;
	}
	
	public RequestDto requestToDto(RegisterRequest registerRequest) {
		RequestDto request=new RequestDto();
		request.setIdDto(registerRequest.getId());

		request.setUsernameDto(registerRequest.getUsername());
		request.setPasswordDto(registerRequest.getPassword());
		request.setEmailDto(registerRequest.getEmail());

		request.setNameDto(registerRequest.getName());
		request.setSurnameDto(registerRequest.getSurname());
		request.setAddressDto(registerRequest.getAdress());
		request.setCityDto(registerRequest.getCity());
		request.setPhoneDto(registerRequest.getPhone());
		
		request.setBiographyDto(registerRequest.getBiography());
		request.setMarkDto(registerRequest.getMark());
		
		request.setCountryDto(registerRequest.getCountry());
		request.setInsuranceNumberDto(registerRequest.getInsuranceNumber());
		
		return request;
	}
	
	public User mappToUser(RequestDto requestDto) {
		User u=new User();
		u.setId(requestDto.getIdDto());

		u.setUsername(requestDto.getUsernameDto());
		u.setPassword(requestDto.getPasswordDto());
		u.setEmail(requestDto.getEmailDto());

		u.setName(requestDto.getNameDto());
		u.setSurname(requestDto.getSurnameDto());
		u.setAdress(requestDto.getAddressDto());
		u.setCity(requestDto.getCityDto());
		u.setPhone(requestDto.getPhoneDto());
		
		u.setBiography(requestDto.getBiographyDto());
		u.setMark(requestDto.getMarkDto());
		
		u.setCountry(requestDto.getCountryDto());
		u.setInsuranceNumber(requestDto.getInsuranceNumberDto());
		return u;
	}
	
	public RegisterRequest mappToRequest(UserDto userDto) {
		RegisterRequest regRequest=new RegisterRequest();
		regRequest.setId(userDto.getIdDto());

		regRequest.setUsername(userDto.getUsernameDto());
		regRequest.setPassword(userDto.getPasswordDto());
		regRequest.setEmail(userDto.getEmailDto());

		regRequest.setName(userDto.getNameDto());
		regRequest.setSurname(userDto.getSurnameDto());
		regRequest.setAdress(userDto.getAddressDto());
		regRequest.setCity(userDto.getCityDto());
		regRequest.setPhone(userDto.getPhoneDto());
		
		regRequest.setBiography(userDto.getBiographyDto());
		regRequest.setMark(userDto.getMarkDto());
		
		regRequest.setCountry(userDto.getCountryDto());
		regRequest.setInsuranceNumber(userDto.getInsuranceNumberDto());
		return regRequest;
	}
	
	public Set<RequestDto> setToDtoSet(Collection<RegisterRequest> registerRequests){
		Set<RequestDto> requestsDto = new HashSet<RequestDto>();

		for (RegisterRequest request : registerRequests) {
			requestsDto.add(this.requestToDto(request));
		}

		return requestsDto;
	}
	
	public Set<RegisterRequest> setToSetRequests(Collection<RequestDto>requestsDto){
		Set<RegisterRequest> registerRequests = new HashSet<RegisterRequest>();

		for (RequestDto requestDto : requestsDto) {
			registerRequests.add(this.dtoToRequest(requestDto));
		}

		return registerRequests;
	}
	
}
