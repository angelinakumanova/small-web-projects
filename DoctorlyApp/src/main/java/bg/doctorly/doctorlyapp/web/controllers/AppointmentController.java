package bg.doctorly.doctorlyapp.web.controllers;


import bg.doctorly.doctorlyapp.data.entites.Appointment;
import bg.doctorly.doctorlyapp.data.entites.Patient;
import bg.doctorly.doctorlyapp.service.entityService.AppointmentService;
import bg.doctorly.doctorlyapp.service.entityService.UserService;
import bg.doctorly.doctorlyapp.service.models.exports.AppointmentStringModel;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public AppointmentController(AppointmentService appointmentService, UserService userService, ModelMapper modelMapper) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/appointments")
    public String appointments(Model model, @AuthenticationPrincipal UserDetails user) {
        Patient userPatient = userService.getPatientByEmail(user.getUsername());

        List<AppointmentStringModel> formatted = userPatient.getAppointments().stream().map(this::mapToAppointment).toList();
        model.addAttribute("appointments", formatted);
        model.addAttribute("title", "Appointments | Doctorly");
        model.addAttribute("pageCss", "/css/appointments.css");


        return "/appointments/appointments";
    }

    private AppointmentStringModel mapToAppointment(Appointment a) {
        AppointmentStringModel map = modelMapper.map(a, AppointmentStringModel.class);
        map.setAppointmentDateTime(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm").format(a.getAppointmentDateTime()));

        return map;
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
