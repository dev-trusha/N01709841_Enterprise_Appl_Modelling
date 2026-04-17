package com.hospital.appointment.controller;

import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for Appointment Service.
 * LocalDate is serialized as "yyyy-MM-dd" string by Jackson + JavaTimeModule.
 */
@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentRestController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> getAll() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Appointment>> search(@RequestParam String type) {
        return ResponseEntity.ok(appointmentService.searchByType(type));
    }

    @PostMapping
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.saveAppointment(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> update(@PathVariable Long id,
                                              @RequestBody Appointment appointment) {
        if (appointmentService.getAppointmentById(id).isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (appointmentService.getAppointmentById(id).isEmpty()) return ResponseEntity.notFound().build();
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
