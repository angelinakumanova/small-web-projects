package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.data.entites.Doctor;
import bg.doctorly.doctorlyapp.service.CityService;
import bg.doctorly.doctorlyapp.service.DoctorService;
import bg.doctorly.doctorlyapp.service.SpecializationService;
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
    private final SpecializationService specializationService;
    private final CityService cityService;

    public SearchController(DoctorService doctorService, SpecializationService specializationService, CityService cityService) {
        this.doctorService = doctorService;
        this.specializationService = specializationService;
        this.cityService = cityService;
    }


    @GetMapping
    public String search(@RequestParam(required = false) String specialization,
                         @RequestParam(required = false) String city,
                         Model model) {
        //TODO: Shared service to load select options
        model.addAttribute("specializations", specializationService.getAll());
        model.addAttribute("cities", cityService.getAll());
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
