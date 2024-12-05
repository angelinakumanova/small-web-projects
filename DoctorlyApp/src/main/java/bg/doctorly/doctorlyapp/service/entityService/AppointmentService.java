package bg.doctorly.doctorlyapp.service.entityService;

import bg.doctorly.doctorlyapp.data.entites.Appointment;

import java.util.Optional;

public interface AppointmentService extends BaseService {

    Optional<Appointment> getAppointmentById(Long id);

    void save(Appointment appointment);

    boolean existsByPatientAndAppointment(Long patientId, Appointment appointment);
}
