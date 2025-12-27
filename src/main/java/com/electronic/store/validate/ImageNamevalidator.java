package com.electronic.store.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageNamevalidator implements ConstraintValidator<ImageNameValid, String> {

    private Logger logger = LoggerFactory.getLogger(ImageNamevalidator.class);
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        logger.info("Message from is valid: {}", value);
        //logic
        return !value.isBlank();
    }

}
