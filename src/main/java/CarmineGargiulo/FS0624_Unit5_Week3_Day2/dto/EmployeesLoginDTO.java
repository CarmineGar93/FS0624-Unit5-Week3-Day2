package CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto;

import jakarta.validation.constraints.*;

public record EmployeesLoginDTO(
        @NotEmpty(message = "Email must be provided")
        @Size(min = 4, max = 16, message = "Username size must be between 4 and 16")
        String username,
        @NotEmpty(message = "Password must be provided")
        @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
        String password) {
}
