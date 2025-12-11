package com.springmvc.beanvalidation.studentregistration.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartsWithConstraintValidator implements ConstraintValidator<StartsWith, String> {
    private String startsWith;

    @Override
    public void initialize(StartsWith constraintAnnotation) {
        startsWith = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s != null){
            return s.startsWith(startsWith);
        }
        return false;
    }
}
