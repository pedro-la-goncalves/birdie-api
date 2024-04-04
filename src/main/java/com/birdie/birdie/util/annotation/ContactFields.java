package com.birdie.birdie.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactFieldsValidation.class)
@Documented
public @interface ContactFields {
    String message() default "contato inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
