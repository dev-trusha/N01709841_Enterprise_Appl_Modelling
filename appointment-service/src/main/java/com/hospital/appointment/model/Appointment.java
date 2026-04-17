package com.hospital.appointment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * JPA entity for the APPOINTMENTS table.
 * appointmentPatientId  → foreign-key reference to Patient Service (:8081)
 * appointmentDoctorId   → foreign-key reference to Doctor  Service (:8082)
 */
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("appointment_id")
    private Long appointmentId;

    @JsonProperty("appointment_number")
    private String appointmentNumber;

    @JsonProperty("appointment_type")
    private String appointmentType;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonProperty("appointment_date")
    private LocalDate appointmentDate;

    @JsonProperty("appointment_description")
    private String appointmentDescription;

    @JsonProperty("appointment_doctor_id")
    private Long appointmentDoctorId;

    @JsonProperty("appointment_patient_id")
    private Long appointmentPatientId;

    // ── Constructors ──────────────────────────────────────────────────────────
    public Appointment() {}

    public Appointment(String appointmentNumber, String appointmentType,
                       LocalDate appointmentDate, String appointmentDescription,
                       Long appointmentDoctorId, Long appointmentPatientId) {
        this.appointmentNumber      = appointmentNumber;
        this.appointmentType        = appointmentType;
        this.appointmentDate        = appointmentDate;
        this.appointmentDescription = appointmentDescription;
        this.appointmentDoctorId    = appointmentDoctorId;
        this.appointmentPatientId   = appointmentPatientId;
    }

    // ── Getters & Setters ─────────────────────────────────────────────────────
    public Long      getAppointmentId()                    { return appointmentId; }
    public void      setAppointmentId(Long v)              { this.appointmentId = v; }

    public String    getAppointmentNumber()                { return appointmentNumber; }
    public void      setAppointmentNumber(String v)        { this.appointmentNumber = v; }

    public String    getAppointmentType()                  { return appointmentType; }
    public void      setAppointmentType(String v)          { this.appointmentType = v; }

    public LocalDate getAppointmentDate()                  { return appointmentDate; }
    public void      setAppointmentDate(LocalDate v)       { this.appointmentDate = v; }

    public String    getAppointmentDescription()           { return appointmentDescription; }
    public void      setAppointmentDescription(String v)   { this.appointmentDescription = v; }

    public Long      getAppointmentDoctorId()              { return appointmentDoctorId; }
    public void      setAppointmentDoctorId(Long v)        { this.appointmentDoctorId = v; }

    public Long      getAppointmentPatientId()             { return appointmentPatientId; }
    public void      setAppointmentPatientId(Long v)       { this.appointmentPatientId = v; }
}
