package ftn.project.services;

import java.util.Set;

import ftn.project.dto.RequestDto;
import ftn.project.dto.UserDto;
import ftn.project.model.RegisterRequest;

public interface RequestService {

	Set<RequestDto> allRequests();

	void acceptRequest( Long id);

	void rejectRequest( Long id);
	
	void saveRequest(UserDto userDto);

}
