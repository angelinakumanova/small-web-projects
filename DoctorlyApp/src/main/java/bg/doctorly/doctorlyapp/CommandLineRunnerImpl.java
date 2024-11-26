package bg.doctorly.doctorlyapp;

import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.PatientService;
import bg.doctorly.doctorlyapp.service.SpecializationService;
import bg.doctorly.doctorlyapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;
    private final SpecializationService specializationService;

    public CommandLineRunnerImpl(DoctorService doctorService, PatientService patientService, UserService userService, SpecializationService specializationService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
        this.specializationService = specializationService;

    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    private void seedDatabase() {
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
