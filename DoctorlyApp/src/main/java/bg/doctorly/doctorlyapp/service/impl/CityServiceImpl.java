package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.entites.City;
import bg.doctorly.doctorlyapp.data.repositories.CityRepository;
import bg.doctorly.doctorlyapp.service.entityService.CityService;
import bg.doctorly.doctorlyapp.service.models.imports.CityImportModel;
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
public class CityServiceImpl implements CityService {
    private final static String FILE_PATH = "src/main/resources/files/cities.json";
    private final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;

    public CityServiceImpl(CityRepository cityRepository, Gson gson, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public void seedData() {
        try (JsonReader jsonReader = new JsonReader(Files.newBufferedReader(Path.of(FILE_PATH)))) {
            CityImportModel[] cities = gson.fromJson(jsonReader, CityImportModel[].class);

            Arrays.stream(cities).map(c -> modelMapper.map(c, City.class)).forEach(cityRepository::save);
            cityRepository.flush();
            System.out.println("Successfully imported " + cities.length + " cities");
        } catch (IOException e) {
            logger.error("Error reading cities data from file: {}", FILE_PATH);
        }
    }

    @Override
    public Optional<City> findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public List<String> getAll() {
        return cityRepository.getAllByOrderByName().stream().map(c -> c.getName()).toList();

    }
}
