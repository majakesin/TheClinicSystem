package ftn.project.services;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.RoomDto;

@Service
public interface RoomService {

	public Set<RoomDto> allRooms();

	public void create(@Valid RoomDto roomDto);

	public void deleteRoom(Long idDto);

	public Set<RoomDto> searchRooms(String nameDto, String hallNumberDto);
	
	public Set<RoomDto> emptyRooms();
	
	public Set<RoomDto>getPacientsRooms(Long pacientId);
	
	RoomDto getRoom(Long id);
	
	public Set<RoomDto> isTermsInRoomTerms(Long idTerm);
	
	public void TakeRoom(AppointmentDto app);
	
	public void takeRoomPredef(AppointmentDto app);

	
}
