package bg.doctorly.doctorlyapp.web.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserRegisterModel {
    @NotNull @Length(min = 2)
    private String firstName;
    @NotNull @Length(min = 2)
    private String lastName;
    @NotNull
    private String dateOfBirth;
    @NotNull @Email(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
    private String email;
    @NotNull @Length(min = 2) @Pattern(regexp = "^[a-zA-Z0-9]$")
    private String password;
    @NotNull @Length(min = 2)
    private String confirmPassword;

    public UserRegisterModel() {
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
