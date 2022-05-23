package sit.integrate.oasip.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
            String errorMessage = error.getDefaultMessage();
            errorsMap.put("Message",errorMessage);
        });
        errors.setErrorMessage(errorsMap);
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = ResponseStatusException.class)
    public ShowException handleNotFoundException(ResponseStatusException ex) {
        ShowException errors= new ShowException();
        errors.setStatusCode(ex.getRawStatusCode());
        if(ex.getRawStatusCode()==500){
            errors.setError("Internal Sever Error");
        }
        else if(ex.getRawStatusCode()==404){
            errors.setError("Not Found");
        }
        else{
            errors.setError(ex.getReason());
        }
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Message",ex.getReason());
        errors.setErrorMessage(errorMap);
        return errors;
    }
}