package bg.doctorly.doctorlyapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    //TODO: Redirect to appointments page after successful log in
    @PostMapping("signup")
    public ModelAndView signupPost() {
        ModelAndView mav = new ModelAndView("/index");

    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
}
