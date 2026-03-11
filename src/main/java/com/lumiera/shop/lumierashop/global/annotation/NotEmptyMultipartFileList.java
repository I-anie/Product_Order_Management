package com.lumiera.shop.lumierashop.global.annotation;

import com.lumiera.shop.lumierashop.global.validator.NotEmptyMultipartFileListValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyMultipartFileListValidator.class)
public @interface NotEmptyMultipartFileList {
    String message() default "{NotEmptyMultipartFileList}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}