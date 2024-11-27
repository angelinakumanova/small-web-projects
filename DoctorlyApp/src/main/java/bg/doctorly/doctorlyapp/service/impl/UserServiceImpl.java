package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.Patient;
import bg.doctorly.doctorlyapp.data.entites.User;
import bg.doctorly.doctorlyapp.data.repositories.UserRepository;
import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.PatientService;
import bg.doctorly.doctorlyapp.service.UserService;
import bg.doctorly.doctorlyapp.service.models.imports.UserImportModel;
import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final static String FILE_PATH = "src/main/resources/files/users.json";
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, DoctorService doctorService, PatientService patientService, Gson gson, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public boolean isImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            UserImportModel[] users = gson.fromJson(jsonReader, UserImportModel[].class);

            Arrays.stream(users).map(this::mapUser).forEach(userRepository::save);
            userRepository.flush();
            System.out.println("Successfully imported " + users.length + " users");
        } catch (IOException e) {
            logger.error("Error reading users data from file: {}", FILE_PATH);
        }
    }

    private User mapUser(UserImportModel u) {
        User map = modelMapper.map(u, User.class);
        String hashedPassword = passwordEncoder.encode(u.getPassword());
        map.setPassword(hashedPassword);

        if (u.getRole().equals("ROLE_DOCTOR")) {
            map.setDoctor(doctorService.getById(u.getEntity()).get());
            return map;
        }

        map.setPatient(patientService.getById(u.getEntity()).get());
        return map;
    }


    @Override
    public boolean validateRegisterModel(UserRegisterModel userRegisterModel) {
        return userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword());
    }

    @Override
    public void registerUser(UserRegisterModel userRegisterModel) {
        User mapped = modelMapper.map(userRegisterModel, User.class);

        Patient mappedPatient = modelMapper.map(userRegisterModel, Patient.class);
        patientService.savePatient(mappedPatient);

        mapped.setPatient(mappedPatient);
        mapped.setRole("ROLE_PATIENT");

        this.userRepository.saveAndFlush(mapped);
    }
}
