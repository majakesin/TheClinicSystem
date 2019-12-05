package ftn.project.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import ftn.project.model.Appointment;

@Service
public interface AppointmentService {

	Set<Appointment> allAppointments();

}
