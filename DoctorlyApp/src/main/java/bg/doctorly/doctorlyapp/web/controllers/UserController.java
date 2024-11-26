package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.service.UserService;
import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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

    //TODO: Redirect to appointments page after successful register
    @PostMapping("/signup")
    public ModelAndView signupPost(@ModelAttribute UserRegisterModel userRegisterModel) {
        ModelAndView mav = new ModelAndView("/");

        if (!userService.validateRegisterModel(userRegisterModel)) {
            mav.setViewName("/user/signup");
            System.out.println("Invalid User");
        }

        this.userService.registerUser(userRegisterModel);
        mav.addObject("isLogged", true);
        System.out.println("Successfully registered user  "
                + userRegisterModel.getFirstName() + " " + userRegisterModel.getLastName());
        return mav;
    }

    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
}
