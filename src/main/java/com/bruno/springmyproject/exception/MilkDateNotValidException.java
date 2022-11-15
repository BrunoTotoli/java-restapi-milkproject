package com.bruno.springmyproject.exception;

public class MilkDateNotValidException extends RuntimeException {
    public MilkDateNotValidException(String message) {
        super(message);
    }
}
