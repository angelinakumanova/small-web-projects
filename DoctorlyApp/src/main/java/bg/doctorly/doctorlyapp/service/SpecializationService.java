package bg.doctorly.doctorlyapp.service;

import bg.doctorly.doctorlyapp.data.entites.Specialization;

import java.util.List;
import java.util.Optional;

public interface SpecializationService extends BaseService {
    Optional<Specialization> findByName(String name);

    List<String> getAll();
}
