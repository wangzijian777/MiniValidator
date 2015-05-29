<a href="http://blog.csdn.net/three_man/" target="_blank">我的博客</a> | <a href="http://url.cn/JEWVPw">点击链接加入群【成都程序员】</a>

##介绍
**本工具目的是为了提供一个极简单的校验框架，该框架满足如下的规则：**
  * 要简单 
只是作为一个小的工具包，代码最多几K，无依赖也是必须的吧
  * 要优雅 
if.else的调用方式太难看了。看看如下的这种怎么样：
new Validator().notNull(name, "姓名").notNull(mail, "邮箱");
  * 要易用 
注解是易用的一个好办法，就像JSR303那样
  * 要可扩展 
要方便客户端程序方便的创建自定义校验器

##整体框架

##如何调用

##如何扩展
