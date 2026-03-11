package com.lumiera.shop.lumierashop.global.annotation;

import com.lumiera.shop.lumierashop.global.validator.NotEmptyMultipartFileValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyMultipartFileValidator.class)
public @interface NotEmptyMultipartFile {
    String message() default "{NotEmptyMultipartFile}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
