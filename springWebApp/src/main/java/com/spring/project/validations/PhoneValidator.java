package com.spring.project.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.matches("[6|8|9]\\d{7}")) {
			return true; 
		}
		else {
		return false;
		}
	}

}
