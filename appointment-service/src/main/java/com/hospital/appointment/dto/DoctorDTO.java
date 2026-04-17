package com.hospital.appointment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO used by RestTemplate to deserialize Doctor JSON from
 * GET http://localhost:8082/api/doctors
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDTO {

    @JsonProperty("doctors_id")
    private Long doctorsId;

    @JsonProperty("doctors_name")
    private String doctorsName;

    @JsonProperty("doctors_email")
    private String doctorsEmail;

    @JsonProperty("doctors_mobile")
    private String doctorsMobile;

    public DoctorDTO() {}

    public Long   getDoctorsId()              { return doctorsId; }
    public void   setDoctorsId(Long v)        { this.doctorsId = v; }

    public String getDoctorsName()            { return doctorsName; }
    public void   setDoctorsName(String v)    { this.doctorsName = v; }

    public String getDoctorsEmail()           { return doctorsEmail; }
    public void   setDoctorsEmail(String v)   { this.doctorsEmail = v; }

    public String getDoctorsMobile()          { return doctorsMobile; }
    public void   setDoctorsMobile(String v)  { this.doctorsMobile = v; }

    @Override
    public String toString() {
        return doctorsName + " (ID: " + doctorsId + ")";
    }
}
