package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.data.repositories.DoctorRepository;
import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.models.DoctorImportModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final static String FILE_PATH = "src/main/resources/files/doctors.json";
    private final static Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    private final DoctorRepository doctorRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public DoctorServiceImpl(DoctorRepository doctorRepository, Gson gson, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
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

            Arrays.stream(doctors).map(d -> modelMapper.map(d, Doctor.class)).forEach(doctorRepository::save);
            doctorRepository.flush();
            System.out.println("Successfully imported " + doctors.length + " doctors");
        } catch (IOException e) {
            logger.error("Error reading doctors data from file: {}", FILE_PATH);
        }
    }
}
