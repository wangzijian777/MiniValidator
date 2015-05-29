package com.prince.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fieldName 字段名
 * value     对比值
 * @author cdwangzijian
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Eq {
	public String fieldName();
	public String value();
}
