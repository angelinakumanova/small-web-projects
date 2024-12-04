package bg.doctorly.doctorlyapp.service.impl.entityServiceImpl;

import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.data.repositories.AppointmentRepository;
import bg.doctorly.doctorlyapp.service.entityService.AppointmentService;
import bg.doctorly.doctorlyapp.service.entityService.DoctorService;
import bg.doctorly.doctorlyapp.service.models.imports.AppointmentImportModel;
import bg.doctorly.doctorlyapp.service.models.imports.DoctorImportModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final static String FILE_PATH = "src/main/resources/files/appointments.json";
    private final static Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorService doctorService, Gson gson, ModelMapper modelMapper) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isImported() {
        return this.appointmentRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            AppointmentImportModel[] appointments = gson.fromJson(jsonReader, AppointmentImportModel[].class);

            Arrays.stream(appointments).map(this::mapToAppointment).forEach(appointmentRepository::save);
            appointmentRepository.flush();
            System.out.println("Successfully imported " + appointments.length + " appointments");
        } catch (IOException e) {
            logger.error("Error reading appointments data from file: {}", FILE_PATH);
        }
    }

    private Appointment mapToAppointment(AppointmentImportModel a) {
        Appointment map = modelMapper.map(a, Appointment.class);
        map.setDoctor(doctorService.getById(a.getDoctor()).get());

        return map;
    }
}
