package bg.doctorly.doctorlyapp;

import bg.doctorly.doctorlyapp.service.DoctorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final DoctorService doctorService;

    public CommandLineRunnerImpl(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!doctorService.isImported()) {
            doctorService.seedData();
        }
    }
}
