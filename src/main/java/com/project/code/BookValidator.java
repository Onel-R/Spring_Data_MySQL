package com.project.code;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;

public class BookValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        
        if (book.getPrice() < 1) {
            errors.rejectValue("price", "price.invalid", "Price must be greater than 0");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Title cannot be empty");
    }
}