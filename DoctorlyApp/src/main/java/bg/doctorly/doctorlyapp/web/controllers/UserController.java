package bg.doctorly.doctorlyapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Sign up | Doctorly");
        model.addAttribute("pageCss", "/css/signup-login.css");
        return "/user/signup";

    }
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("title", "Login | Doctorly");
        model.addAttribute("pageCss", "/css/signup-login.css");

        if (error != null) {
            model.addAttribute("loginError", true);
        }
        return "/user/login";
    }


}
