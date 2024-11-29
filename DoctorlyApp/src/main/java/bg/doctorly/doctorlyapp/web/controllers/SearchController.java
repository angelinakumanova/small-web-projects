package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.service.SharedDataService;
import bg.doctorly.doctorlyapp.service.entityService.CityService;
import bg.doctorly.doctorlyapp.service.entityService.DoctorService;
import bg.doctorly.doctorlyapp.service.entityService.SpecializationService;
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
    private final SharedDataService sharedDataService;

    public SearchController(DoctorService doctorService, SharedDataService sharedDataService) {
        this.doctorService = doctorService;
        this.sharedDataService = sharedDataService;
    }


    @GetMapping
    public String search(@RequestParam(required = false) String specialization,
                         @RequestParam(required = false) String city,
                         Model model) {
        sharedDataService.loadSearchOptions(model);
        model.addAttribute("currentSpec", specialization);
        model.addAttribute("currentCity", city);

        List<Doctor> doctors = doctorService.searchDoctors(specialization, city);

        model.addAttribute("doctors", doctors);

        if (doctors.isEmpty()) {
            model.addAttribute("message", "No doctors available for specific criteria");
        }
        return "/doctorsDisplay/doctors";
    }
}
