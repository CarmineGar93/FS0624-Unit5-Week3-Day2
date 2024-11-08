package CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
        @NotEmpty(message = "Username must be provided")
        @Size(min = 4, max = 16, message = "Username size must be between 4 and 16")
        String username,
        @NotEmpty(message = "Name must be provided")
        @Size(min = 3, max = 40, message = "Name size must be between 3 and 40")
        String name,
        @NotEmpty(message = "Surname must be provided")
        @Size(min = 3, max = 40, message = "Surname size must be between 3 and 40")
        String surname,
        @NotEmpty(message = "Email must be provided")
        @Email(message = "Email not valid")
        String email,
        @NotEmpty(message = "Password must be provided")
        @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
        String password) {

}
