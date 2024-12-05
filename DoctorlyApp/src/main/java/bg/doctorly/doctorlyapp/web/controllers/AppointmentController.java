package bg.doctorly.doctorlyapp.web.controllers;


import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.data.entites.Patient;
import bg.doctorly.doctorlyapp.service.entityService.AppointmentService;
import bg.doctorly.doctorlyapp.service.entityService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @PostMapping("/book-appointment/{id}")
    @ResponseBody
    public ResponseEntity<?> bookAppointment(@PathVariable Long id,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Please log in"));
        }

        Patient userPatient = userService.getPatientByEmail(userDetails.getUsername());
        Appointment appointment = appointmentService.getAppointmentById(id).orElse(null);

        if (appointment != null) {
            if (!appointmentService.existsByPatientAndAppointment(userPatient.getId(), appointment)) {
                appointment.setPatient(userPatient);
                appointmentService.save(appointment);
                return ResponseEntity.ok(Map.of("message", "Appointment booked successfully!"));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "You already have an appointment for this time."));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Appointment not found!"));
    }
}
