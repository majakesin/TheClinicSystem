package ftn.project.services;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import ftn.project.dto.RoomDto;
import ftn.project.model.Room;

@Service
public interface RoomService {

	public Set<RoomDto> allRooms();

	public void create(@Valid RoomDto roomDto);

	public void deleteRoom(Long idDto);

	public Set<RoomDto> searchRooms(String nameDto, String hallNumberDto);
}
