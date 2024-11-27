package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.City;
import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.data.entites.Specialization;
import bg.doctorly.doctorlyapp.data.repositories.DoctorRepository;
import bg.doctorly.doctorlyapp.service.CityService;
import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.SpecializationService;
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
import java.util.List;
import java.util.Optional;

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
    public List<Doctor> searchDoctors(String specialization, String city) {
        Specialization spec = specializationService.findByName(specialization).orElse(null);
        City cityObj = cityService.findByName(city).orElse(null);

        if (spec == null && cityObj == null) {
            return doctorRepository.findAll();
        } else if (spec != null && cityObj != null) {
            return doctorRepository.findBySpecializationAndCity(spec, cityObj);
        } else if (spec != null) {
            return doctorRepository.findBySpecialization(spec);
        } else {
            return doctorRepository.findByCity(cityObj);
        }

    }
}
