package CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record TripDTO(
        @NotEmpty(message = "Destination must be provided")
        @Size(min = 3, message = "Destination must have more than two characters")
        String destination,
        @NotEmpty(message = "Date must be provided")
        String date
        /*@Pattern(regexp = "IN_PROGRESS|COMPLETED", message = "State must be IN_PROGESS or COMPLETED")
        String status*/) {
}
