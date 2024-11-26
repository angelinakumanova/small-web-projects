package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.Specialization;
import bg.doctorly.doctorlyapp.service.models.exports.SpecializationNameExport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Optional<Specialization> findByName(String name);

    List<SpecializationNameExport> getAllByOrderByName();
}
