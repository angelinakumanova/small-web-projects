package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
