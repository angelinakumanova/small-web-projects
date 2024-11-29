package bg.doctorly.doctorlyapp.web.controllers;

import bg.doctorly.doctorlyapp.service.entityService.UserService;
import bg.doctorly.doctorlyapp.web.models.UserRegisterModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/validate")
public class SignUpValidationController {

    private final UserService userService;

    public SignUpValidationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> validateSignup(@RequestBody UserRegisterModel user) {
        Set<String> invalidFields = new HashSet<>();

        if (user.getFirstName().trim().isEmpty() || user.getFirstName().length() < 2) {
            invalidFields.add("floatingFirstName");
        }

        if (user.getLastName().trim().isEmpty() || user.getLastName().length() < 2) {
            invalidFields.add("floatingLastName");
        }

        if (user.getDateOfBirth().trim().isEmpty()) {
            invalidFields.add("floatingDate");
        } else {
            LocalDate birthDate = LocalDate.parse(user.getDateOfBirth());
            LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

            if (birthDate.isAfter(eighteenYearsAgo)) {
                invalidFields.add("floatingDate");
            }
        }

        if (user.getEmail().trim().isEmpty() || !user.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
                || userService.findByEmail(user.getEmail())) {
            invalidFields.add("floatingInput");
        }

        if (user.getPassword().trim().isEmpty() || user.getPassword().length() < 6) {
            invalidFields.add("floatingPassword");
        }

        if (!user.getPassword().equals(user.getConfirmPassword()) || user.getConfirmPassword().trim().isEmpty()) {
           invalidFields.add("floatingConfirmPassword");
        }

        if (!invalidFields.isEmpty()) {
            return ResponseEntity.badRequest().body(invalidFields);
        }

        userService.registerUser(user);

        return ResponseEntity.ok(Map.of("message", "User successfully registered."));
    }
}
