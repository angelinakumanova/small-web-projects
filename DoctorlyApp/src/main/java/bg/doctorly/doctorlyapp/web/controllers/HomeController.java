package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.service.CityService;
import bg.doctorly.doctorlyapp.service.SpecializationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final SpecializationService specializationService;
    private final CityService cityService;

    public HomeController(SpecializationService specializationService, CityService cityService) {
        this.specializationService = specializationService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Home | Doctorly");
        model.addAttribute("specializations", specializationService.getAll());
        model.addAttribute("cities", cityService.getAll());
        return "index";
    }

    //TODO: When appointments page is done
//    @GetMapping("/search")
//    public String showSearchPage(Model model) {
//
//    }
}
