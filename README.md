<a href="http://blog.csdn.net/three_man/" target="_blank">我的博客</a> | <a href="http://url.cn/JEWVPw">点击链接加入群【成都程序员】</a>

##介绍
**本工具目的是为了提供一个极简单的校验框架，该框架满足如下的规则：**
  * 要简单  
只是作为一个小的工具包，代码最多几K，无依赖也是必须的吧 
  * 要优雅  
if.else的调用方式太难看了。看看如下的这种怎么样：  
```Java
new Validator().notNull(name, "姓名").notNull(mail, "邮箱");
```
  * 要易用  
注解是易用的一个好办法，就像JSR303那样
  * 要可扩展  
要方便客户端程序方便的创建自定义校验器

##整体框架
![image](https://raw.githubusercontent.com/wangzijian777/MiniValidator/master/pic/struts.png) 

1. 用来给对象进行注解的Annotation及其解析器和校验器

* Annotation ,一组注解 
* Parser, 注解解析器，主要处理注解的行为 
* AnnotationValidator 使用注解和解析器对传入的对象的字段进行校验

2. 可扩展的校验器 

* AnnotationRule 注解校验rule,作为内置的rule使用 
* Rule 用于扩展，可以自定义Rule 
* Validator 使用Rule对数据进行校验，或者使用内置的校验器

##如何调用
###使用注解进行校验：
一个待校验的类：
```Java
class User{
    private Long id;
    @NotBlank(fieldName="姓名")
    private String name;
    @Less(fieldName="年龄", value=100)
    private int age;

    private String phone;
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
```
校验测试代码：
```Java
public class TestAnnotationValidator {
    public static void main(String[] args) {
        User user = new User();
        ValidateResult result = AnnotationValidator.validate(user);
        if(result.isValid()){
            System.out.println("校验通过");
        }else{
            System.out.println(result.getMessage());
        }
    }
}
```

###使用通用校验器：
```Java
public class TestValidator {
    public static void main(String[] args) {
        testMethod("name", null, null, null);
    }

    public static void testMethod(String name, String mail, String thirdOrderId, String address){
        Validator v = new Validator().notNull(name, "姓名").notNull(mail, "邮箱").notNull(address, "地址");
        if(v.isValid()){
            System.out.println("校验通过");
        }else{
            System.out.println(v.getMessage());
        }
    }
}
```
##如何扩展
###扩展注解：
* 添加一个注解：
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    public String fieldName();
    public String format();
}
```
* 添加解析器
```Java
/**
 * 日期格式注解解析器
 * 
 * @author cdwangzijian
 *
 */
public class DateFormatParser implements IAnnotationParser{
    /**
     * 校验f字段的值是否符合value的日期格式
     * @see DateFormat
     */
    @Override
    public ValidateResult validate(Field f, Object value) {
        ValidateResult result = new ValidateResult();
        if(f.isAnnotationPresent(DateFormat.class)){
            DateFormat dateFormat = f.getAnnotation(DateFormat.class);
            try {
                if(value != null){
                    SimpleDateFormat format = new SimpleDateFormat(dateFormat.format());
                    format.parse(value.toString());
                }
            } catch (ParseException e) {
                result.setMessage(dateFormat.fieldName() + "不满足格式：" + dateFormat.format());
            }   
        }
        return result;
    }
}
```
* 使用新解析器的测试程序：
```Java
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
```

###扩展通用校验器
* 自定义一个Rule

```Java
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
```
* 使用这个Rule
```Java
public class TestValidator {
    public static void main(String[] args) {
        new Validator().validate(new AnnotationRule(new User()));
    }
}
```
