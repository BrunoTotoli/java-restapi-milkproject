package com.bruno.springmyproject.util;

import javax.validation.ConstraintViolation;
import java.util.Set;
import java.util.stream.Collectors;

public class ConstraintViolationsToString {

    private ConstraintViolationsToString() {
        throw new IllegalStateException("Util class");
    }

    public static String formatConstraintViolationsToString(Set<ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
    }


}
