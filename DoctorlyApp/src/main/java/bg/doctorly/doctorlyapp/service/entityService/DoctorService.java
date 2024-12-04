package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.service.models.exports.DoctorSearchModel;

import java.util.List;
import java.util.Optional;

public interface DoctorService extends BaseService{
    Optional<Doctor> getById(Long id);

    List<DoctorSearchModel> searchDoctors(String specialization, String city);
}
