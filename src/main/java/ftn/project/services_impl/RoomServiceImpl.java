package ftn.project.services_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.RoomDto;
import ftn.project.mapper.RoomMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Room;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.RoomRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.AppointmentService;
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
	
	private final UserRepository userRepository;
	
	private final AppointmentService appointmentService;
	
	private final AppoitmentRepository appointmentRepository;
	
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

	@Override
	public Set<RoomDto> getPacientsRooms(Long id) {
		List<Long>roomsId=new ArrayList<Long>();
		for(Appointment a:appointmentRepository.findAllByPacientId(id)) {
			roomsId.add(a.getRoomId());
		}
		return roomMapper.setRoomToDto(roomRepository.findAllById(roomsId));
	}
	
}
