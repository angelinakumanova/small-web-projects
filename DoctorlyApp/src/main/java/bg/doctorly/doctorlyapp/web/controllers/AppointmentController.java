package bg.doctorly.doctorlyapp.web.controllers;


import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.service.entityService.AppointmentService;
import bg.doctorly.doctorlyapp.service.entityService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("/book-appointment/{id}")
    public String bookAppointment(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {

        Appointment appointment = appointmentService.getAppointmentById(id).orElse(null);
        if (appointment != null) {
            appointment.setPatient(userService.getPatientByEmail(userDetails.getUsername()));
            appointmentService.save(appointment);
            return "redirect:/";
        }

        return "redirect:users/login";
    }
}
