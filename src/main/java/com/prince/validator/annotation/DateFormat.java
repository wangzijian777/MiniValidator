package com.prince.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * fieldName 字段名称
 * format	 日期格式, 要符合SimpleDateFormat的标准
 * @author cdwangzijian
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
	public String fieldName();
	public String format();
}
