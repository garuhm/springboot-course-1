package com.springmvc.beanvalidation.studentregistration.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StartsWithConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartsWith {
    public String value() default "ENG";
    public String message() default "must start with ENG";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
