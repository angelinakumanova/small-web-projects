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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/login")
    public ModelAndView loginPost(@ModelAttribute UserLoginModel userLoginModel) {
        ModelAndView mav = new ModelAndView("redirect:/");

        return mav;
    }
}
