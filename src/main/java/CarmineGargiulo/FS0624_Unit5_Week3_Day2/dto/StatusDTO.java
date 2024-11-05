package CarmineGargiulo.FS0624_Unit5_Week3_Day2.dto;

import jakarta.validation.constraints.Pattern;

public record StatusDTO(
        @Pattern(regexp = "IN_PROGRAM|ASSIGNED|COMPLETED", message = "Status must be IN_PROGESS, ASSIGNED or " +
                "COMPLETED") String status) {
}
