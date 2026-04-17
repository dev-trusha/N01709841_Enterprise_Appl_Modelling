package com.hospital.appointment.service;

import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implements addAppointments, editAppointments, deleteAppointments, searchAppointments
 * from the class diagram.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /** addAppointments() */
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    /** editAppointments() */
    public Appointment updateAppointment(Long id, Appointment updated) {
        updated.setAppointmentId(id);
        return appointmentRepository.save(updated);
    }

    /** deleteAppointments() */
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    /** searchAppointments() — all records */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /** searchAppointments() — by id */
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    /** searchAppointments() — by type keyword */
    public List<Appointment> searchByType(String type) {
        return appointmentRepository.findByAppointmentTypeContainingIgnoreCase(type);
    }
}
