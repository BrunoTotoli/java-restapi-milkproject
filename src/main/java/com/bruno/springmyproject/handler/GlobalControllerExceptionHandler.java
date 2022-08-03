package com.bruno.springmyproject.handler;


import com.bruno.springmyproject.exception.ExceptionDetails;
import com.bruno.springmyproject.exception.MilkNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(MilkNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleMilkNotFoundException(MilkNotFoundException milkNotFoundException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Milk Not Found, find other id")
                .errorMessage(milkNotFoundException.getMessage())
                .errorClass(milkNotFoundException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);

    }

}
