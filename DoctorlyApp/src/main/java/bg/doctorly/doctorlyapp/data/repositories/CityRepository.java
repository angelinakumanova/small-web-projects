package bg.doctorly.doctorlyapp.data.repositories;

import bg.doctorly.doctorlyapp.data.entites.City;
import bg.doctorly.doctorlyapp.service.models.exports.CityNameExport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);


    List<CityNameExport> getAllByOrderByName();
}
