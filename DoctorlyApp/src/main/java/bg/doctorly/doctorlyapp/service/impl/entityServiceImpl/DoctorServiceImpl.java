package bg.doctorly.doctorlyapp.service.impl.entityServiceImpl;

import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.data.entites.City;
import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.data.entites.Specialization;
import bg.doctorly.doctorlyapp.data.repositories.DoctorRepository;
import bg.doctorly.doctorlyapp.service.entityService.CityService;
import bg.doctorly.doctorlyapp.service.entityService.DoctorService;
import bg.doctorly.doctorlyapp.service.entityService.SpecializationService;
import bg.doctorly.doctorlyapp.service.models.exports.AppointmentSearchModel;
import bg.doctorly.doctorlyapp.service.models.exports.DoctorSearchModel;
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
import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final static String FILE_PATH = "src/main/resources/files/doctors.json";
    private final static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final DoctorRepository doctorRepository;
    private final SpecializationService specializationService;
    private final CityService cityService;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, SpecializationService specializationService, CityService cityService, Gson gson, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.specializationService = specializationService;
        this.cityService = cityService;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isImported() {
        return this.doctorRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            DoctorImportModel[] doctors = gson.fromJson(jsonReader, DoctorImportModel[].class);

            Arrays.stream(doctors).map(this::mapToDoctor).forEach(doctorRepository::save);
            doctorRepository.flush();
            System.out.println("Successfully imported " + doctors.length + " doctors");
        } catch (IOException e) {
            logger.error("Error reading doctors data from file: {}", FILE_PATH);
        }
    }

    private Doctor mapToDoctor(DoctorImportModel d) {
        Doctor map = modelMapper.map(d, Doctor.class);
        map.setSpecialization(specializationService.findByName(d.getSpecialization()).get());
        map.setCity(cityService.findByName(d.getCity()).get());

        return map;
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorRepository.findById(id);

    }

    @Override
    public List<DoctorSearchModel> searchDoctors(String specialization, String city) {
        List<Doctor> doctors;
        Specialization spec = specializationService.findByName(specialization).orElse(null);
        City cityObj = cityService.findByName(city).orElse(null);

        if (spec == null && cityObj == null) {
            doctors = doctorRepository.findAll();
        } else if (spec != null && cityObj != null) {
            doctors = doctorRepository.findBySpecializationAndCity(spec, cityObj);
        } else if (spec != null) {
            doctors = doctorRepository.findBySpecialization(spec);
        } else {
            doctors = doctorRepository.findByCity(cityObj);
        }

        return doctors.stream().map(this::mapDoctors).toList();

    }

    private DoctorSearchModel mapDoctors(Doctor d) {
        List<Appointment> appointments = d.getAppointments().stream().filter(a -> a.getPatient() == null).toList();
        List<AppointmentSearchModel> filteredAppointments = appointments.stream()
                .sorted(Comparator.comparing(Appointment::getAppointmentDateTime))
                .map(a -> modelMapper.map(a, AppointmentSearchModel.class))
                .toList();
        DoctorSearchModel map = modelMapper.map(d, DoctorSearchModel.class);
        map.setAppointments(filteredAppointments);

        return map;
    }
}
