package com.hospital.appointment.model;

/**
 * Read-only view model combining a local Appointment with resolved
 * patient/doctor names fetched from the other two microservices.
 */
public class AppointmentView {

    private final Appointment appointment;
    private final String patientName;
    private final String doctorName;

    public AppointmentView(Appointment appointment, String patientName, String doctorName) {
        this.appointment = appointment;
        this.patientName = patientName;
        this.doctorName  = doctorName;
    }

    public Appointment getAppointment() { return appointment; }
    public String      getPatientName() { return patientName; }
    public String      getDoctorName()  { return doctorName;  }
}
