package ftn.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftn.project.dto.AppointmentDto;
import ftn.project.mapper.AppointmentMapper;
import ftn.project.mapper.RoomMapper;
import ftn.project.model.Appointment;
import ftn.project.model.Room;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.RoomRepository;
import ftn.project.services.RequestService;
import ftn.project.services.RoomService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class ShendulingExamServiceTest {

	@Autowired
	private RoomService roomService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AppoitmentRepository appoitmentRepository;
	
	@Autowired 
	private AppointmentMapper appoitmentMapper;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Test
	public void testGetAllShendulingRequest() {
		Set<AppointmentDto>appointments=requestService.allNotAcceptedMaja();
		Set<AppointmentDto>appointTestAppointmentDtos=appoitmentMapper.setToDtoSet(appoitmentRepository.findAllByisAccept(false));
		assertThat(appointments).isNotNull();
		assertThat(appointments).isNotEmpty();
		assertThat(appointTestAppointmentDtos).isNotEmpty();
		assertThat(appointTestAppointmentDtos).isNotNull();
	}
	
	@Test
	public void testAppointmentDateOfRoom() {
		Appointment appoint=appoitmentRepository.findById(33L).get();
		List<Room>allRooms=roomRepository.findAll();
		
		assertThat(appoint).isNotNull();
		assertThat(allRooms).isNotEmpty();
		assertThat(appoint).isNotNull();
		
	}
}
