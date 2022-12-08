package com.tutorials.msbooking.handler;

import com.tutorials.msbooking.model.ErrorResponseModel;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseModel> handle(Exception exception) {
        log.error("Exception thrown:", exception);
        if (exception instanceof MethodArgumentNotValidException ex) {
            var message = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
            return generateErrorResponse(message, 9001);
        }
        return generateErrorResponse(exception.getMessage(), 9999);
    }

    private ResponseEntity<ErrorResponseModel> generateErrorResponse(String message, int code) {
        return new ResponseEntity<>(
                ErrorResponseModel.builder().message(message).code(code).build(),
                HttpStatus.BAD_REQUEST);
    }
}
