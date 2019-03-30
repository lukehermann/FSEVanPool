package com.springboot.configuration;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch , Object> {

    private String field;
    private String fieldMatch;


    @Override
    public void initialize(FieldMatch fieldsValueMatch) {
        this.field=fieldsValueMatch.field();
        this.fieldMatch=fieldsValueMatch.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {


        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        if (fieldValue != null) {
            return fieldValue.equals(fieldMatchValue);
        } else {
            return fieldMatchValue == null;
        }
    }
}