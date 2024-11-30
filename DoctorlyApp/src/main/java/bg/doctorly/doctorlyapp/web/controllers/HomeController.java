package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.service.SharedDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final SharedDataService sharedDataService;

    public HomeController(SharedDataService sharedDataService) {
        this.sharedDataService = sharedDataService;
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Home | Doctorly");
        model.addAttribute("pageCss", "/css/styles.css");
        sharedDataService.loadSearchOptions(model);

        return "index";
    }

    //TODO: When appointments page is done
//    @GetMapping("/search")
//    public String showSearchPage(Model model) {
//
//    }
}
