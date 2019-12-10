package ftn.project.mapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import ftn.project.dto.AppointmentDto;
import ftn.project.dto.RequestDto;
import ftn.project.model.Appointment;
import ftn.project.model.RegisterRequest;
import lombok.Data;

@Data
@Component
public class AppointmentMapper {

	public Appointment dtoToAppointment(AppointmentDto appointmentDto) {
		Appointment appointment = new Appointment();
		
		appointment.setId(appointmentDto.getIdDto());
		appointment.setDate(appointmentDto.getDateDto());
		appointment.setTime(appointmentDto.getTimeDto());
		appointment.setRoom(appointmentDto.getRoomDto());
		appointment.setType(appointmentDto.getTypeDto());
		appointment.setPrice(appointmentDto.getPriceDto());
		appointment.setDiscount(appointmentDto.getDiscountDto());
		
		
		return appointment;
	}
	
	public AppointmentDto appointmentToDto(Appointment appointment) {
		AppointmentDto appointmentDto = new AppointmentDto();
		
		appointmentDto.setIdDto(appointment.getId());
		appointmentDto.setDateDto(appointment.getDate());
		appointmentDto.setTimeDto(appointment.getTime());
		appointmentDto.setRoomDto(appointment.getRoom());
		appointmentDto.setTypeDto(appointment.getType());
		appointmentDto.setPriceDto(appointment.getPrice());
		appointmentDto.setDiscountDto(appointment.getDiscount());
		
		return appointmentDto;
	}
	
	public Set<AppointmentDto> setToDtoSet(Collection<Appointment> appointments){
		Set<AppointmentDto> appointmentDto = new HashSet<AppointmentDto>();

		for (Appointment appointment : appointments) {
			appointmentDto.add(this.appointmentToDto(appointment));
		}

		return appointmentDto;
	}
	
	public Set<Appointment> setToSetAppointments(Collection<AppointmentDto> appointmentsDto){
		Set<Appointment> appointments = new HashSet<Appointment>();

		for (AppointmentDto appointmentDto : appointmentsDto) {
			appointments.add(this.dtoToAppointment(appointmentDto));
		}

		return appointments;
	}
}
