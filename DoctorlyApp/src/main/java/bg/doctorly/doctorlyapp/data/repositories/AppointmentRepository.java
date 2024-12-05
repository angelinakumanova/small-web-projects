package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByAppointmentDateTimeAndPatientId(LocalDateTime appointmentDateTime, Long patientId);
}
