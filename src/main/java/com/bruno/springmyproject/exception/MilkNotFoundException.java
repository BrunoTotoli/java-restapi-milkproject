package com.bruno.springmyproject.exception;

public class MilkNotFoundException extends RuntimeException {
    public MilkNotFoundException(String message) {
        super(message);
    }
}
