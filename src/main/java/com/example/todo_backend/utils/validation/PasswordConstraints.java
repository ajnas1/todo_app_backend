package com.example.todo_backend.utils.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Documented
@Constraint(validatedBy = PasswordConstrainstsValidator.class)
@Target( { ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordConstraints{
    String message() default "Password must be graterthan 4 and lessthan 10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
