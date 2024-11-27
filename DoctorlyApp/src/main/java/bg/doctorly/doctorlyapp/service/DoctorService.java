package bg.doctorly.doctorlyapp.service;

import bg.doctorly.doctorlyapp.data.entites.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService extends BaseService{
    Optional<Doctor> getById(Long id);

    List<Doctor> searchDoctors(String specialization, String city);
}
