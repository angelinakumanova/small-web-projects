package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.service.entityService.UserService;
import bg.doctorly.doctorlyapp.web.models.UserLoginModel;
import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {


    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
}
