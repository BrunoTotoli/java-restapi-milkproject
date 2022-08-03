package com.bruno.springmyproject.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private LocalDateTime timestamp;
    private int status;
    private String errorMessage;
    private String errorClass;
    private String title;

}
