package bg.doctorly.doctorlyapp;

import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.PatientService;
import bg.doctorly.doctorlyapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;
    private final UserService userService;

    public CommandLineRunnerImpl(DoctorService doctorService, PatientService patientService, UserService userService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedDatabase();
    }

    private void seedDatabase() {
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
