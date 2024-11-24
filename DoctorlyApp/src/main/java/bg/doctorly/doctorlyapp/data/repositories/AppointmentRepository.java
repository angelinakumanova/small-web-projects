package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
