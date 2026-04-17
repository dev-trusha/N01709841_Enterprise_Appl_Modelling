package com.hospital.appointment;

import com.hospital.appointment.model.Appointment;
import com.hospital.appointment.repository.AppointmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class AppointmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(AppointmentRepository repo) {
        return args -> {
            repo.save(new Appointment("APT-001", "General Checkup",
                    LocalDate.of(2025, 5, 10), "Routine annual checkup", 1L, 1L));
            repo.save(new Appointment("APT-002", "Cardiology",
                    LocalDate.of(2025, 5, 15), "ECG and stress test",    2L, 2L));
            repo.save(new Appointment("APT-003", "Dermatology",
                    LocalDate.of(2025, 5, 20), "Skin rash examination",  3L, 3L));
        };
    }
}
