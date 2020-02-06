package ftn.project.services_impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.dto.OperationDto;
import ftn.project.model.Appointment;
import ftn.project.model.Room;
import ftn.project.model.User;
import ftn.project.repository.AppoitmentRepository;
import ftn.project.repository.OperationRepository;
import ftn.project.repository.RoomRepository;
import ftn.project.repository.UserRepository;
import ftn.project.services.EmailService;
import ftn.project.services.OperationService;
import ftn.project.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

	private final EmailService emailService;

	private final AppoitmentRepository appointmentRepository;

	private final OperationRepository operationRepository;

	private final UserService userService;

	private final UserRepository userRepository;

	private final RoomRepository roomRepository;

	@Override
	public void scheduleOperation() {
		// TODO Auto-generated method stub

		emailService.sendMail("prolomercegovac@yahoo.com", "Operacija odobrena", "Operacija odobrena");

	}

	@Override
	public void createOperation(OperationDto operationDto,boolean isOperation) {
		Appointment appointment = appointmentRepository.findById(operationDto.getTermId()).get();
		appointment.setAccept(true);
		List<User> doctors = userRepository.findAllById(operationDto.getOperationIds());
		appointment.setDoctors(doctors);
		appointment.setRoomId(operationDto.getRoomDto());
		if(isOperation) {
			appointment.setDoctor(doctors.get(0).getId());
		}
		appointmentRepository.save(appointment);
		for (User u : doctors) {
			if (u.getEmail() != null) {
				emailService.sendMail(u.getEmail(), "Postovani imate zakazanu " + appointment.getOperationType()
						+ " , pogledajte vas radni kalendar", appointment.getOperationType());
			}
		}
		Room room = roomRepository.findById(operationDto.getRoomDto()).get();
		room.setFree(false);
		roomRepository.save(room);
		User pacient = userRepository.findById(appointment.getPacientId()).get();
		emailService.sendMail(pacient.getEmail(), "Postovani vas termin je zakazan", appointment.getOperationType());
	}

	public void automaticSystem() {
		List<Appointment> appointmentsWithotRoom = appointmentRepository.findAllByRoomId(null);
		List<Long> idOfRooms = new ArrayList<Long>();
		Random rand = new Random();
		List<Room> rooms = roomRepository.findAll();
		Set<User> doctors = userRepository.findAllByRole("doktor");
		List<Long> docId = new ArrayList<Long>();

		List<Long> takenRooms = new ArrayList<Long>();
		List<Long> takenDoctors = new ArrayList<Long>();
		for (User u : doctors) {
			docId.add(u.getId());
		}

		for (Room r : rooms) {
			idOfRooms.add(r.getId());
		}

		for (Appointment a : appointmentsWithotRoom) {
			Long randRoom = idOfRooms.get(idOfRooms.size() - 1);
			Long randDoc = docId.get(docId.size() - 1);

			takenRooms.add(randRoom);
			takenDoctors.add(randDoc);

			a.setRoomId(randRoom);
			a.setDoctor(randDoc);
		}
		appointmentRepository.saveAll(appointmentsWithotRoom);

		List<Room> roomsForTake = roomRepository.findAllById(takenRooms);
		List<User> doctorsForEmail = userRepository.findAllById(takenDoctors);

		for (Room rm : roomsForTake) {
			rm.setFree(true);
		}

		roomRepository.saveAll(roomsForTake);

		for (User us : doctorsForEmail) {
			if (us.getEmail() != null) {
				emailService.sendMail(us.getEmail(), "Pogledajte vas kalendar", "Obaveza");

			}
		}
	}

	@Override
	public Long fullRooms(Long id) {
		Appointment selectedAppointment = appointmentRepository.findById(id).get();
		List<Appointment> allDates = appointmentRepository.findAll();
		Appointment comparator = new Appointment();
		Collections.sort(allDates);
		return allDates.get(0).getRoomId();

	}

	@Override
	public void changeOperation(Long idTerm, String time, String date,Long room) {
		Appointment appointment=appointmentRepository.findById(idTerm).get();
		User pacient=userRepository.findById(appointment.getPacientId()).get();
		
		appointment.setDate(date);
		appointment.setRoomId(room);
		appointment.setTime(time);
		appointmentRepository.save(appointment);
		emailService.sendMail(pacient.getEmail(),"Termin pomeren za "+appointment.getDate()+" u "+appointment.getTime()+"h", appointment.getOperationType());
		
	}

	@Override
	public void createExamination(Long idTerm, String time, String date, Long room) {
		// TODO Auto-generated method stub
		
	}




	

}
