package bg.doctorly.doctorlyapp.service.impl;

import bg.doctorly.doctorlyapp.data.repositories.PatientRepository;
import bg.doctorly.doctorlyapp.service.PatientService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final static String FILE_PATH = "";

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

    }
}
