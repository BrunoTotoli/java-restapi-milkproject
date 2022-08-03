package com.bruno.springmyproject.util;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
public class ConstraintViolationsToString {

    public static String formatConstraintViolationsToString(Set<ConstraintViolation<?>> constraintViolations) {
        StringBuilder builder = new StringBuilder();
        List<String> errors = new ArrayList<>(constraintViolations.size());
        String error;

        for (ConstraintViolation<?> cont : constraintViolations) {
            error = cont.getMessage();
            errors.add(error);
        }

        for (int i = 0; i < errors.size(); i++) {
            builder.append(errors.get(i));
            if (i == (errors.size()) - 1) {
                builder.append(".");
                continue;
            }
            builder.append(", ");

        }

        return builder.toString();

    }


}
