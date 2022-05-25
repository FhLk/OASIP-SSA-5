package sit.integrate.oasip.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ShowException handleValidationException(MethodArgumentNotValidException ex) {
        ShowException errors=new ShowException();
        Map<String, String> errorsMap = new HashMap<>();
        errors.setStatusCode(400);
        errors.setError("BAD REQUEST");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMap.put(fieldName,errorMessage);
        });
        errors.setErrorMessage(errorsMap);
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookingException.class)
    public ShowException handleBusinessException(BookingException ex) {
        ShowException errors=new ShowException();
        errors.setStatusCode(500);
        errors.setError("INTERNAL SERVER ERROR");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", ex.getMessage());
        errors.setErrorMessage(errorMap);
        return errors;
    }
}