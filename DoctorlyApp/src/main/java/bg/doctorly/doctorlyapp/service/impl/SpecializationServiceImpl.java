package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.service.SpecializationService;
import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    private final static String FILE_PATH = "src/main/resources/files/cities.json";


    @Override
    public boolean isImported() {
        return false;
    }

    @Override
    public void seedData() {

    }
}
