package com.prince.validator;

import com.prince.validator.annotation.DateFormat;
import com.prince.validator.annotation.Less;
import com.prince.validator.annotation.NotBlank;
import com.prince.validator.annotation.parser.DateFormatParser;
import com.prince.validator.annotation.parser.ValidateResult;

class User{
	private Long id;
	@NotBlank(fieldName="姓名")
	private String name;
	@Less(fieldName="年龄", value=100)
	private int age;
	
	private String phone;
	@DateFormat(fieldName="生日", format="yyyy-MM-dd")
	private String birthday;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}

public class TestAnnotationValidator {
	public static void main(String[] args) {
		User user = new User();
		user.setName("wzj");
		user.setAge(21);
		user.setBirthday("20150525");
		AnnotationValidator.register(new DateFormatParser());
		ValidateResult result = AnnotationValidator.validate(user);
		if(result.isValid()){
			System.out.println("校验通过");
		}else{
			System.out.println(result.getMessage());
		}
	}
}
