package com.lumiera.shop.lumierashop.global.validator;

import com.lumiera.shop.lumierashop.global.annotation.NotEmptyMultipartFileList;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class NotEmptyMultipartFileListValidator
        implements ConstraintValidator<NotEmptyMultipartFileList, List<MultipartFile>> {

    @Override
    public boolean isValid(List<MultipartFile> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return false;

        return value.stream().anyMatch(file -> file != null && !file.isEmpty());
    }
}