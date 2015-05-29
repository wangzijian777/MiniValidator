package com.prince.validator.Rule;

import com.prince.validator.AnnotationValidator;
import com.prince.validator.annotation.parser.ValidateResult;

/**
 * 使用AnnotationValidator的校验规则
 * 
 * @see AnnotationValidator
 * @author cdwangzijian
 *
 */
public class AnnotationRule implements Rule{
	private String message;
	private Object o;
	
	public AnnotationRule(Object o) {
		this.o = o;
	}
	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public boolean isValid() {
		ValidateResult result = AnnotationValidator.validate(this.o);
		this.message = result.getMessage();
		return result.isValid();
	}
	
}
