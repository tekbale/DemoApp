package test.tek.demo1.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import test.tek.demo1.exceptions.ExceptionResponse;
import test.tek.demo1.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class TimeClockExceptionHandler {
    @ExceptionHandler(value = { ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException() {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setMessage("Not found");
        return exceptionResponse;
    }
}
