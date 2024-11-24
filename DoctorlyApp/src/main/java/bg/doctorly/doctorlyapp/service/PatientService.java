package bg.doctorly.doctorlyapp.service;

import bg.doctorly.doctorlyapp.data.entites.Patient;

import java.util.Optional;

public interface PatientService extends BaseService{
    Optional<Patient> getById(Long id);
}
