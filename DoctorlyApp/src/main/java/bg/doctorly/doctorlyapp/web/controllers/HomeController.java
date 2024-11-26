package bg.doctorly.doctorlyapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("specializations", List.of("Cardiologist", "Dermatologist"));
        model.addAttribute("cities", List.of("Sofia", "Plovdiv"));
        return "index";
    }

    //TODO: When appointments page is done
//    @GetMapping("/search")
//    public String showSearchPage(Model model) {
//
//    }
}
