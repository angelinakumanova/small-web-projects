package bg.doctorly.doctorlyapp.service.models.imports;


public class AppointmentImportModel {
    private String appointmentDateTime;
    private Long doctor;

    public AppointmentImportModel() {}

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(String appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }
}
