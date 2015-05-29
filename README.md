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

##如何调用
使用注解进行校验：
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

使用通用校验器：
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
