package sit.integrate.oasip.exeption;

import org.springframework.http.HttpStatus;
<<<<<<< HEAD
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
>>>>>>> localhost

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
<<<<<<< HEAD
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
=======
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ShowException handleValidationExceptions(MethodArgumentNotValidException ex) {
        ShowException errors=new ShowException();
        Map<String, String> errorsMap = new HashMap<>();
        errors.setStatusCode("400");
        errors.setError("BAD REQUEST");
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorsMap.put(fieldName,errorMessage);
        });
        errors.setErrorField(errorsMap);
        return errors;
>>>>>>> localhost
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookingNotFoundException.class)
<<<<<<< HEAD
    public Map<String, String> handleBusinessException(BookingNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
=======
    public ShowException handleBusinessException(BookingNotFoundException ex) {
        ShowException errors=new ShowException();
        errors.setStatusCode("500");
        errors.setError("INTERNAL SERVER ERROR");
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message", ex.getMessage());
        errors.setErrorField(errorMap);
        return errors;
>>>>>>> localhost
    }
}