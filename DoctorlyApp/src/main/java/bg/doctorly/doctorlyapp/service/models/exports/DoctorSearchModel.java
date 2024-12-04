package bg.doctorly.doctorlyapp.service.models.exports;


import java.util.List;

public class DoctorSearchModel {
    private String firstName;
    private String lastName;
    private String cityName;
    private String profilePictureUrl;
    private String specializationName;
    private String description;
    private String phoneNumber;
    private List<AppointmentSearchModel> appointments;

    public DoctorSearchModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getSpecializationName() {
        return specializationName;
    }

    public void setSpecializationName(String specializationName) {
        this.specializationName = specializationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AppointmentSearchModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentSearchModel> appointments) {
        this.appointments = appointments;
    }
}
