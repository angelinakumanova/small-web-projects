package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.data.entites.Patient;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PatientService extends BaseService{
    Optional<Patient> getById(Long id);

    void savePatient(Patient patient);

}
