package sit.integrate.oasip.exeption;

import org.springframework.web.server.ResponseStatusException;

public class BookingException extends Exception {
    public BookingException(String message) {
        super(message);
    }
}
