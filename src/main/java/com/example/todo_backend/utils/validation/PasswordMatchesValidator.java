package com.example.todo_backend.utils.validation;

import com.example.todo_backend.model.SignUpModel;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpModel> {
    
    @Override
    public void initialize(PasswordMatches password) {

    }
    @Override
    public boolean isValid(SignUpModel userModel, ConstraintValidatorContext context) {
        if (userModel == null) {
            return true;
        }

        boolean isValid = userModel.getPassword() != null &&
                          userModel.getPassword().equals(userModel.getConformPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Password and confirm password must match")
                   .addPropertyNode("conformPassword") 
                   .addConstraintViolation();
        }

        return isValid;
    }
    
}
