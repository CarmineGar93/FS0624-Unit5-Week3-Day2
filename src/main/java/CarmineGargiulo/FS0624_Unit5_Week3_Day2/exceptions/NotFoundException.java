package CarmineGargiulo.FS0624_Unit5_Week3_Day2.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id, String resource) {
        super(resource + " with id " + id + " does not exists");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
