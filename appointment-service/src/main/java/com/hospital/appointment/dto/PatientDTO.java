package com.hospital.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO used by RestTemplate to deserialize Patient JSON from
 * GET http://localhost:8081/api/patients
 *
 * @JsonIgnoreProperties(ignoreUnknown=true) silently discards
 * fields the appointment service doesn't need (password, address, etc.).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO {

    @JsonProperty("patient_id")
    private Long patientId;

    @JsonProperty("patient_name")
    private String patientName;

    @JsonProperty("patient_email")
    private String patientEmail;

    @JsonProperty("patient_mobile")
    private String patientMobile;

    public PatientDTO() {}

    public Long   getPatientId()             { return patientId; }
    public void   setPatientId(Long v)       { this.patientId = v; }

    public String getPatientName()           { return patientName; }
    public void   setPatientName(String v)   { this.patientName = v; }

    public String getPatientEmail()          { return patientEmail; }
    public void   setPatientEmail(String v)  { this.patientEmail = v; }

    public String getPatientMobile()         { return patientMobile; }
    public void   setPatientMobile(String v) { this.patientMobile = v; }

    @Override
    public String toString() {
        return patientName + " (ID: " + patientId + ")";
    }
}
