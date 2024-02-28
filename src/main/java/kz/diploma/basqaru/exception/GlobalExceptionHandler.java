package kz.diploma.basqaru.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ElementNotFoundException.class)
    public Map<String, String> elementNotFound(ElementNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransmittedNullException.class)
    public Map<String, String> transmittingNull(TransmittedNullException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", exception.getMessage());
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncorrectOperationTypeException.class)
    public Map<String, String> incorrectOperationType(IncorrectOperationTypeException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("Error", exception.getMessage());
        return error;
    }

}
