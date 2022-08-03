package com.bruno.springmyproject.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String errorMessage;
    private String errorClass;
}
