package com.prince.validator;

import com.prince.validator.Rule.AnnotationRule;

public class TestValidator {
	public static void main(String[] args) {
		new Validator().validate(new AnnotationRule(new User()));
	}
}
