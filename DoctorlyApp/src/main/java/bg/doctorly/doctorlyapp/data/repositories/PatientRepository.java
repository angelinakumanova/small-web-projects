package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.data.entites.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
