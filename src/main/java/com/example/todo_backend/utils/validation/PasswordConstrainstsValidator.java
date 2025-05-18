package com.example.todo_backend.utils.validation;

import com.example.todo_backend.model.entity.UserModel;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstrainstsValidator implements ConstraintValidator<PasswordConstraints, UserModel>{
    @Override
    public void initialize(PasswordConstraints password) {

    }

     @Override
    public boolean isValid(UserModel userModel, ConstraintValidatorContext context) {
        if (userModel == null) {
            return true;
        }

        boolean isValid = userModel.getPassword().length() > 4 
                &&  userModel.getPassword().length() < 10;

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Please enter strong password")
                   .addPropertyNode("password") 
                   .addConstraintViolation();
        }

        return isValid;
    }
}
