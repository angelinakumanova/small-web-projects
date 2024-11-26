package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.Specialization;
import bg.doctorly.doctorlyapp.data.repositories.SpecializationRepository;
import bg.doctorly.doctorlyapp.service.SpecializationService;
import bg.doctorly.doctorlyapp.service.models.imports.SpecializationImportModel;
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
public class SpecializationServiceImpl implements SpecializationService {
    private final static String FILE_PATH = "src/main/resources/files/specializations.json";
    private final static Logger logger = LoggerFactory.getLogger(SpecializationServiceImpl.class);

    private final SpecializationRepository specializationRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public SpecializationServiceImpl(SpecializationRepository specializationRepository, Gson gson, ModelMapper modelMapper) {
        this.specializationRepository = specializationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isImported() {
        return this.specializationRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            SpecializationImportModel[] specializations = gson.fromJson(jsonReader, SpecializationImportModel[].class);

            Arrays.stream(specializations).map(s -> modelMapper.map(s, Specialization.class)).forEach(specializationRepository::save);
            specializationRepository.flush();
            System.out.println("Successfully imported " + specializations.length + " specializations");
        } catch (IOException e) {
            logger.error("Error reading specializations data from file: {}", FILE_PATH);
        }
    }

    @Override
    public Optional<Specialization> findByName(String name) {
        return specializationRepository.findByName(name);
    }

    @Override
    public List<String> getAll() {
        return specializationRepository.getAllByOrderByName().stream().map(s -> s.getName()).toList();

    }
}
