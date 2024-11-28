package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.Patient;
import bg.doctorly.doctorlyapp.data.repositories.PatientRepository;
import bg.doctorly.doctorlyapp.service.entityService.PatientService;
import bg.doctorly.doctorlyapp.service.models.imports.PatientImportModel;
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
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final static String FILE_PATH = "src/main/resources/files/patients.json";
    private final static Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public PatientServiceImpl(PatientRepository patientRepository, Gson gson, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isImported() {
        return this.patientRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            PatientImportModel[] patients = gson.fromJson(jsonReader, PatientImportModel[].class);

            Arrays.stream(patients).map(p -> modelMapper.map(p, Patient.class)).forEach(patientRepository::save);
            patientRepository.flush();
            System.out.println("Successfully imported " + patients.length + " patients");
        } catch (IOException e) {
            logger.error("Error reading patients data from file: {}", FILE_PATH);
        }
    }

    @Override
    public Optional<Patient> getById(Long id) {

        return patientRepository.findById(id);
    }

    @Override
    public void savePatient(Patient patient) {
        this.patientRepository.saveAndFlush(patient);
    }
}
