package com.prince.validator.annotation.parser;

import java.lang.reflect.Field;

import com.prince.validator.annotation.NotNull;

/**
 * 为空校验器
 * @author cdwangzijian
 *
 */
public class NotNullParser implements IAnnotationParser {
	/**
	 * 校验字段f的值不能为null，校验结果保存在result中
	 */
	@Override
	public ValidateResult validate(Field f, Object value) {
		ValidateResult result = new ValidateResult();
		if(f.isAnnotationPresent(NotNull.class)){
			NotNull notNull = f.getAnnotation(NotNull.class);
			if(value == null){
				result.setMessage(notNull.fieldName() + "不能为空");
			}
		}
		return result;
	}
}
