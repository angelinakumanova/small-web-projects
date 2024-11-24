package bg.doctorly.doctorlyapp;

import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DoctorService doctorService;
    private final PatientService patientService;

    public CommandLineRunnerImpl(DoctorService doctorService, PatientService patientService) {
        this.doctorService = doctorService;
        this.patientService = patientService;
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
    }
}
