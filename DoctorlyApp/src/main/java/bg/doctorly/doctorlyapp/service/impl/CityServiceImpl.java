package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.repositories.CityRepository;
import bg.doctorly.doctorlyapp.service.CityService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    private final static String FILE_PATH = "src/main/resources/files/cities.json";

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

    }
}
