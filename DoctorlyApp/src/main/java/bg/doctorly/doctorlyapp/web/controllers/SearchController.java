package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final DoctorService doctorService;

    public SearchController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    @GetMapping
    public String search(@RequestParam(required = false) String specialization,
                         @RequestParam(required = false) String city,
                         Model model) {
        List<Doctor> doctors = doctorService.searchDoctors(specialization, city);

        model.addAttribute("doctors", doctors);
        if (doctors.isEmpty()) {
            model.addAttribute("message", "No doctors available for specific criteria");
        }
        return "/doctorsDisplay/doctors";
    }
}
