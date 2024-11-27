package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.City;
import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.data.entites.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecializationAndCity(Specialization specialization, City city);

    List<Doctor> findBySpecialization(Specialization specialization);

    List<Doctor> findByCity(City city);
}
