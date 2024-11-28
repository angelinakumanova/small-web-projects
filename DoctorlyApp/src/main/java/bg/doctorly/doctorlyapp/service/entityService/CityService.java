package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.data.entites.City;

import java.util.List;
import java.util.Optional;

public interface CityService extends BaseService{
    Optional<City> findByName(String name);

    List<String> getAll();
}
