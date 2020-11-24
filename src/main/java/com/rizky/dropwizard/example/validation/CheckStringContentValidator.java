package com.rizky.dropwizard.example.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class CheckStringContentValidator implements 
ConstraintValidator<CheckStringContent, String> {

    @Override
    public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
        
        if(arg0.contains("John"))   {
            return true;
        }
        else    {
            return false;
        }
    }
    

}
