package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Optional<Specialization> findByName(String name);
}
