package com.springboot.configuration;

import com.springboot.model.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

   @Override
   public void initialize(PasswordMatches passwordMatches) {
   }

   @Override
   public boolean isValid(Object obj, ConstraintValidatorContext context) {
      User userDto = (User) obj;
      return userDto.getPassword().equals(userDto.getConfirmPassword());
   }
}