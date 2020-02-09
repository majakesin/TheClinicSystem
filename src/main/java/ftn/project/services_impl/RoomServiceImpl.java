package ftn.project.services_impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.BusyTermDto;
import ftn.project.dto.RoomDto;
import ftn.project.mapper.RoomMapper;
import ftn.project.model.Appointment;
import ftn.project.model.BusyTerms;
import ftn.project.model.Room;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.BusyTermsRepository;
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

	
	private final RoomRepository roomRepository;

	@Autowired
	private RoomMapper roomMapper;

	private final UserRepository userRepository;
	
	private final BusyTermsRepository busyTermsRepository;

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

		if (nameDto != "" && hallNumberDto == "") {
			Set<Room> sobeTemp = roomRepository.findByName(nameDto);
			sobe = roomMapper.setRoomToDto(sobeTemp);
			return sobe;
		}

		if (nameDto != "" && hallNumberDto != "") {
			Set<Room> sobeTemp = roomRepository.findByNameAndHallNumber(nameDto, hallNumberDto);
			sobe = roomMapper.setRoomToDto(sobeTemp);
			return sobe;
		}

		if (nameDto == "" && hallNumberDto != "") {
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
		List<Long> roomsId = new ArrayList<Long>();
		for (Appointment a : appointmentRepository.findAllByPacientId(id)) {
			roomsId.add(a.getRoomId());
		}
		return roomMapper.setRoomToDto(roomRepository.findAllById(roomsId));
	}

	@Override
	public Set<RoomDto> isTermsInRoomTerms(Long idTerm) {
		Appointment appoint=appointmentRepository.findById(idTerm).get();
		List<Room>allRooms=roomRepository.findAll();
		
		String[] appTime=appoint.getTime().split(":");
		int appHours=Integer.parseInt(appTime[0]);
		int appMin=Integer.parseInt(appTime[1]);
		
		int allMinutes=(appHours*60)+appMin;
		
		List<Room>freeRooms=allRooms;
		List<Room>roomForRemove=new ArrayList<Room>();
		for(Room r:allRooms) {
			for(BusyTerms terms:r.getTerms()) {
			
				
				String[] timeString=terms.getTime().split(":");
				int hours=Integer.parseInt(timeString[0]);
				int min=Integer.parseInt(timeString[1]);
				int allMin=(hours*60)+min;
				
				int apsolute=Math.abs(allMinutes-allMin);
				
				if(terms.getDate()==appoint.getDate()) {
					if(apsolute<30) {
						roomForRemove.add(r);
						break;
					}			
				}
			}
			
		}
		freeRooms.removeAll(roomForRemove);
		return roomMapper.setRoomToDto(freeRooms);
	}

	@Override
	public void TakeRoom(AppointmentDto app) {
		Appointment appoint=appointmentRepository.findById(app.getIdDto()).get();
		Room room=roomRepository.findById(app.getRoomId()).get();
		
		List<BusyTerms>busyList=new ArrayList<BusyTerms>();
		BusyTerms bt=new BusyTerms();
		bt.setDate(appoint.getDate());
		bt.setTime(appoint.getTime());
		
		busyList.add(bt);
		
		room.getTerms().add(bt);
		appoint.setRoomId(room.getId());
		busyTermsRepository.save(bt);
		appointmentRepository.save(appoint);
		roomRepository.save(room);
		
		
	}

}
