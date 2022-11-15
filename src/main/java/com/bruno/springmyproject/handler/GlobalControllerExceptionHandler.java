package com.bruno.springmyproject.handler;


import com.bruno.springmyproject.exception.ExceptionDetails;
import com.bruno.springmyproject.exception.MilkDateNotValidException;
import com.bruno.springmyproject.exception.MilkNotFoundException;
import com.bruno.springmyproject.util.ConstraintViolationsToString;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails> handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Invalid Fields")
                .errorMessage(ConstraintViolationsToString.formatConstraintViolationsToString(constraintViolationException.getConstraintViolations()))
                .errorClass(constraintViolationException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MilkDateNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleMilkDateNotExistsException(MilkDateNotValidException milkDateNotValidException) {
        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Invalid Date")
                .errorMessage(milkDateNotValidException.getMessage())
                .errorClass(milkDateNotValidException.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
