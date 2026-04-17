package com.hospital.appointment.controller;

import com.hospital.appointment.dto.DoctorDTO;
import com.hospital.appointment.dto.PatientDTO;
import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.model.AppointmentView;
import com.hospital.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Thymeleaf MVC controller for Appointment UI.
 *
 * Inter-service communication (RestTemplate):
 *   fetchAllPatients() → GET http://localhost:8081/api/patients  (Patient Service)
 *   fetchAllDoctors()  → GET http://localhost:8082/api/doctors   (Doctor  Service)
 *
 * These calls populate drop-down lists on the form and resolve names on the list page.
 * If a service is unreachable the method returns an empty list — pages degrade gracefully.
 */
@Controller
public class AppointmentController {

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    @Value("${doctor.service.url}")
    private String doctorServiceUrl;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RestTemplate restTemplate;

    // ── Remote-service helpers ────────────────────────────────────────────────

    private List<PatientDTO> fetchAllPatients() {
        try {
            ResponseEntity<List<PatientDTO>> res = restTemplate.exchange(
                    patientServiceUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {});
            return res.getBody() != null ? res.getBody() : Collections.emptyList();
        } catch (Exception e) {
            System.err.println("[AppointmentController] Patient Service unavailable: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private List<DoctorDTO> fetchAllDoctors() {
        try {
            ResponseEntity<List<DoctorDTO>> res = restTemplate.exchange(
                    doctorServiceUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<>() {});
            return res.getBody() != null ? res.getBody() : Collections.emptyList();
        } catch (Exception e) {
            System.err.println("[AppointmentController] Doctor Service unavailable: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /** Joins appointment rows with names resolved from the other services */
    private List<AppointmentView> buildViews(List<Appointment> list,
                                              List<PatientDTO>  patients,
                                              List<DoctorDTO>   doctors) {
        return list.stream().map(a -> {
            String pName = patients.stream()
                    .filter(p -> p.getPatientId() != null && p.getPatientId().equals(a.getAppointmentPatientId()))
                    .map(PatientDTO::getPatientName).findFirst().orElse("N/A");
            String dName = doctors.stream()
                    .filter(d -> d.getDoctorsId() != null && d.getDoctorsId().equals(a.getAppointmentDoctorId()))
                    .map(DoctorDTO::getDoctorsName).findFirst().orElse("N/A");
            return new AppointmentView(a, pName, dName);
        }).collect(Collectors.toList());
    }

    // ── Root redirect ──────────────────────────────────────────────────────────
    @GetMapping("/")
    public String root() { return "redirect:/appointments"; }

    // ── READ ──────────────────────────────────────────────────────────────────
    @GetMapping("/appointments")
    public String list(Model model) {
        model.addAttribute("appointmentViews",
                buildViews(appointmentService.getAllAppointments(),
                           fetchAllPatients(), fetchAllDoctors()));
        return "appointments/list";
    }

    // ── SEARCH ────────────────────────────────────────────────────────────────
    @GetMapping("/appointments/search")
    public String search(@RequestParam(defaultValue = "") String type, Model model) {
        List<Appointment> results = type.isBlank()
                ? appointmentService.getAllAppointments()
                : appointmentService.searchByType(type);
        model.addAttribute("appointmentViews",
                buildViews(results, fetchAllPatients(), fetchAllDoctors()));
        model.addAttribute("searchType", type);
        return "appointments/list";
    }

    // ── CREATE ────────────────────────────────────────────────────────────────
    @GetMapping("/appointments/add")
    public String addForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", fetchAllPatients());
        model.addAttribute("doctors",  fetchAllDoctors());
        return "appointments/form";
    }

    @PostMapping("/appointments/add")
    public String add(@ModelAttribute Appointment appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    // ── UPDATE ────────────────────────────────────────────────────────────────
    @GetMapping("/appointments/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found: " + id)));
        model.addAttribute("patients", fetchAllPatients());
        model.addAttribute("doctors",  fetchAllDoctors());
        return "appointments/form";
    }

    @PostMapping("/appointments/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments";
    }

    // ── DELETE ────────────────────────────────────────────────────────────────
    @GetMapping("/appointments/delete/{id}")
    public String delete(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }
}
