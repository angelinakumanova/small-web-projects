package bg.doctorly.doctorlyapp.service.models.exports;

public class AppointmentSearchModel {
    private Long id;
    private String appointmentDateTime;

    public AppointmentSearchModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}
