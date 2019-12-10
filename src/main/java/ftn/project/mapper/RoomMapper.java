package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.RoomDto;
import ftn.project.model.Room;
import lombok.Data;

@Component
@Data
public class RoomMapper {
	
	public RoomDto roomToDto (Room room) {
		
		RoomDto roomDto = new RoomDto();
		roomDto.setIdDto(room.getId());
		roomDto.setHallNumberDto(room.getHallNumber());
		roomDto.setNameDto(room.getName());
		
		return roomDto;
		
	}
	
	public Room dtoToRoom (RoomDto roomDto) {
		
		Room room = new Room();
		room.setId(roomDto.getIdDto());
		room.setHallNumber(roomDto.getHallNumberDto());
		room.setName(roomDto.getNameDto());
		
		return room;
	}
	
	public Set<RoomDto> setRoomToDto (Collection<Room> rooms) {
		
		Set<RoomDto> roomsDto = new HashSet<RoomDto>();// ovako se instancira set

		for (Room room : rooms) {
			roomsDto.add(this.roomToDto(room));
		}
		return roomsDto;
		
	}
	
public Set<Room> setDtoToRoom (Collection<RoomDto> roomsDto) {
		
		Set<Room> rooms = new HashSet<Room>();// ovako se instancira set

		for (RoomDto roomDto : roomsDto) {
			rooms.add(this.dtoToRoom(roomDto));
		}
		return rooms;
		
	}

	
}
