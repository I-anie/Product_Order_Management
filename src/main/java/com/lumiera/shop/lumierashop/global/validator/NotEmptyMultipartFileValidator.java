package com.lumiera.shop.lumierashop.global.validator;

import com.lumiera.shop.lumierashop.global.annotation.NotEmptyMultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class NotEmptyMultipartFileValidator
        implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return value != null && !value.isEmpty();
    }
}