package CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EmployeesLoginDTO(
        @NotNull(message = "Email must be provided")
        @Email(message = "Email not valid")
        String email,
        @NotEmpty(message = "Username must be provided")
        @Size(min = 4, max = 16, message = "Username size must be between 4 and 16")
        String username) {
}
