package com.spring.project.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = PhoneValidator.class)

public @interface Phone {
	String message() default "{Phone}"; 
	Class<?>[] groups() default{}; 
	Class<? extends Payload>[] payload() default {}; 

}
