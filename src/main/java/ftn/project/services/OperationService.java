package ftn.project.services;

import java.util.Collection;

import ftn.project.dto.RoomDto;
import ftn.project.dto.UserDto;

public interface OperationService {

	void scheduleOperation(RoomDto roomDto,Collection<UserDto> doctors);
	
}
