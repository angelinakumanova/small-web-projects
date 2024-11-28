package bg.doctorly.doctorlyapp;

import bg.doctorly.doctorlyapp.service.entityService.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;
    private final SpecializationService specializationService;
    private final CityService cityService;

    public CommandLineRunnerImpl(DoctorService doctorService, PatientService patientService, UserService userService, SpecializationService specializationService, CityService cityService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
        this.specializationService = specializationService;

        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    private void seedDatabase() {
        if (!cityService.isImported()) {
            cityService.seedData();
        }

        if (!specializationService.isImported()) {
            specializationService.seedData();
        }

        if (!doctorService.isImported()) {
            doctorService.seedData();
        }

        if(!patientService.isImported()) {
            patientService.seedData();
        }

        if(!userService.isImported()) {
            userService.seedData();
        }
    }
}
