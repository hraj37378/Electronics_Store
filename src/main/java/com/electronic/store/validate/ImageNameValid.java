package com.electronic.store.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNamevalidator.class)
public @interface ImageNameValid {
    // error message
    String message() default "Invalid Image Name!!";

    // represent group of constraints
    Class<?>[] groups() default {};

    // additional info about annotation
    Class<? extends Payload>[] payload() default {};

}
