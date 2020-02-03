package ftn.project.services_impl;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.RoomDto;
import ftn.project.mapper.RoomMapper;
import ftn.project.model.Room;
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

	@Override
	public Set<RoomDto> searchRooms(String nameDto, String hallNumberDto) {
		Set<RoomDto> sobe = null;
		
		if(nameDto!="" && hallNumberDto=="") {
			Set<Room> sobeTemp = roomRepository.findByName(nameDto);
			sobe = roomMapper.setRoomToDto(sobeTemp);
			return sobe;
		}
		

		if(nameDto!="" && hallNumberDto!="") {
			Set<Room> sobeTemp = roomRepository.findByNameAndHallNumber(nameDto,hallNumberDto);
			sobe = roomMapper.setRoomToDto(sobeTemp);
			return sobe;
		}

		if(nameDto=="" && hallNumberDto!="") {
			Set<Room> sobeTemp = roomRepository.findByHallNumber(hallNumberDto);
			sobe = roomMapper.setRoomToDto(sobeTemp);
			return sobe;
		}
		return sobe;
		
	}

	@Override
	public Set<RoomDto> emptyRooms() {
		return roomMapper.setRoomToDto(roomRepository.findAllByFree(true));
	}

	@Override
	public RoomDto getRoom(Long id) {
		return roomMapper.roomToDto(roomRepository.findById(id).get());
	}
	
}
