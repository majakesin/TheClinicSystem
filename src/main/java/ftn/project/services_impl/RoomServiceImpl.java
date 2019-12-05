package ftn.project.services_impl;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.RoomDto;
import ftn.project.mapper.RoomMapper;
import ftn.project.repository.RoomRepository;
import ftn.project.services.RoomService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

	

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomMapper roomMapper; 
	
	@Override
	public Set<RoomDto> allRooms() {
		 return roomMapper.setRoomToDto(roomRepository.findAll()); 
		
	}

	@Override
	public void create(@Valid RoomDto roomDto) {
		roomRepository.save(roomMapper.dtoToRoom(roomDto));
		
	}

	@Override
	public void deleteRoom(Long idDto) {
		roomRepository.deleteById(idDto);
		
	}
	
}
