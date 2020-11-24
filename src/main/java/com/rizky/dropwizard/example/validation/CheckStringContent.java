package com.rizky.dropwizard.example.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=CheckStringContentValidator.class)
public @interface CheckStringContent {
    String message() default "incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
