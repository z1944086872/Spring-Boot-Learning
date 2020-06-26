## 配置文件

### 1、配置文件

Spring Boot使用一个全局的配置文件，配置文件名是固定的

- application.properties
- application.yml



配置文件的作用：修改Spring Boot自动配置的默认值

Spring Boot在底层都给我们配置好；



YAML

​	YAML（A Markup Language）：一个标记语言

​	YAML（isn't Markup Language）不是标记语言

标记语言：

​	以前的配置文件：大多使用 *.xml文件

​	Yaml：以数据为中心，比json，xml等更适合做配置文件

YAML：

```yaml
server:
  port: 8081
```

xml：

```xml
<server>
    <port>8081</port>
</server>
```

### 2、YAML语法：

#### 1、基本语法

K:  V    ：表示一对键值对（空格必须有）；

以空格的缩进来控制层次关系，只要是左对齐的一列数据，都是同一个层级的

```yml
server:
	port:
	path:


```

属性和值也是大小写敏感

#### 2、值的写法

##### 字面量：普通的值（数字，字符串，布尔）

​		K:   V  ：字面直接来写

​						字符串默认不用加单引号和双引号；

​						""：双引号，不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思

​								name:  "zhangsan \n lisi"，输出 ：zhangsan 换行 lisi

​						''：单引号，会转义特殊字符，特殊字符最终只是一个普通的字符串时数据

​								name:  "zhangsan \n lisi"，输出 ：zhangsan \n lisi

##### 对象，Map（属性和值）（键值对）：

​	K:  V ：在下一行来写对象的属性和值的关系，注意缩进

​		对象还是K:  V 的方式

缩进写法：

```yaml
userinfo:
	account:123456
	username:"张三"
	age:20
```

行内写法：

```yaml
userinfo: {account: 123456,username: "张三",age: 20}
```

##### 数组（List、Set）：

用 “- 值”表示数组中的一个元素

缩进写法：

```yaml
username:
 - "张三"
 - "李四"
 - "王五"
```

行内写法：

```yaml
username: {"张三","李四","王五"}
```

3、配置文件注入

配置文件

```yaml
userinfo:
  account: 11111
  username: "张三"
  age: 20
  sex: true
  accountAndPaswd:
    11111: 123456
  list:
     - "李四"
     - "王五"
     - "赵六"
    
```

JavaBean

```Java
@Component
@ConfigurationProperties(prefix = "userinfo")
public class UserInfo {
    private Long account;//账号
    private String username;//姓名
    private Integer age;//年龄
    private Boolean sex;//性别

    private Map<Long,String> accountAndPaswd;
    private List<Object> list;
}
```

我们导入配置文件处理器之后可以配置提示

```xml
<!-- 导入配置文件处理器 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
     <optional>true</optional>
</dependency>
```

### 3、配置文件注入

###### 1、@Value 获取值与 @ConfigurationProperties获取值比较

|                      | @ConfigurationProperties | @Value       |
| -------------------- | ------------------------ | ------------ |
| 功能                 | 批量注入配置文件中的属性 | 一个一个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持       |
| SpEL                 | 不支持                   | 支持         |
| JSR303数据校验       | 支持                     | 不支持       |
| 复杂类型封装         | 支持                     | 不支持       |

如果只在某个业务逻辑中只需要获取某个值，就可以使用@Value

配置文件yml还是properties都可以获取到值：

###### 2、配置文件数据校验

```java
@Component
@ConfigurationProperties(prefix = "userinfo")
public class UserInfo {
    private Long account;//账号
    private String username;//姓名
    private Integer age;//年龄
    private Boolean sex;//性别
    @Email//邮箱校验
    private String email;
}
```

###### 3、加载指定的配置文件

@PropertySource与@ImportResource：



@PropertySource

```java
@PropertySource(value = "classpath:userinfo.properties")
@Component
@ConfigurationProperties(prefix = "userinfo")
public class UserInfo {
    private Long account;//账号
    private String username;//姓名
    private Integer age;//年龄
    private Boolean sex;//性别
    @Email//邮箱校验
    private String email;
}
```

@ImportResource，导入Spring的配置文件

Spring Boot里面没有Spring的配置文件，自己编写的配置文件，也不能自动识别；

想让Spring的配置文件生效，需要将**@ImportResource**标注在一个配置类上



```java
@ImportResource(locations = {"classpath:userbean.xml"})
//导入Spring的配置文件让其生效
```



不编写Spring配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


    <bean id= "helloService" class="com.example.demo1.service.HelloService"></bean>
</beans>
```

Spring Boot推荐给容器中添加组件的方法（推荐使用全注解方法）：

1、配置类======Spring配置文件

2、使用@Bean给容器添加组件

```java

/**
 * @Configuration指明当前类是一个配置类
 * 替代Spring配置文件
 */
@Configuration
public class UserConfig {

//    将方法的返回值添加在组件中，组件的默认ID就是方法名
    @Bean
    public UserService userService(){
        return new UserService();
    }
}
```

### 4、配置文件占位符

```properties
UserInfo.account = ${random.uuid}
```

### 5、Profile

#### 1、多Profile文件

在主配置文件编写的时候，文件名可以是application-{profile}.propertoes/yml

默认使用application.properties的配置

#### 2、yml支持多文档块

```yml
UserInfo:
  account: 11111
  username: "张三"
  age: 20
  sex: true
  accountAndPaswd:
    11111: 123456
  list:
    - "李四"
    - "王五"
    - "赵六"

spring:
  profiles:
    active: dev
---
service:
  port: 8081
spring:
  profiles:dev

---
server:
  port: 8082
```



#### 3、激活指定profile

##### 在配置文件中指定：

```properties
spring.profiles.active=dev
```

##### 命令行指定:

--spring.profiles.active=dev

##### 虚拟机参数：

-Dspring.profiles.active=dev

### 6、配置文件加载位置

SpringBoot启动会扫描一下位置的application.properties或者application.yml文件作为SpringBoot的默认配置文件

-file../config/

-file../

-classpath:/config/

-classpath:/

优先级由高到低，高优先级会覆盖低优先级的配置

SpringBoot会从这四个位置全部加载配置文件；**互补配置**；

还可以通过spring.config.location来改变默认的配置文件位置

### 7、外部配置加载顺序

**SpringBoot也可以从以下位置加载配置；优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置**

##### 1、命令行参数：

```c
java -jar 文件名.jar --servicr.port=8083 --service.context-path=/demo1
```

多个配置用空格分开；--配置项=值



##### 2、来自java:comp/env的JNDI属性

##### 3、Java系统属性（System.getProperties()）

##### 4、操作系统环境

##### 5、RandomValuePropertySource配置的random.*属性值

**<u>由jar包外向内进行寻找；优先加载带profile</u>**

##### 6、jar包外部的application-{profile}.properties或application.yml（带spring.profile）配置文件

##### 7、jar包内部的application-{profile}.properties或application.yml（带spring.profile）配置文件

##### 8、jar包外部的application-{profile}.properties或application.yml（不带spring.profile）配置文件

##### 9、jar包内部的application-{profile}.properties或application.yml（不带spring.profile）配置文件

##### 10、@Configuration注解类上的@PropertySource

##### 11、通过SpringApplication.setDefaultProperties指定的默认配置

### 8、自动配置原理

##### 1、SpringBoot启动的时候加载主配置类，开启了自动配置功能@EnalbeAutoConfiguration

##### 2、@EnalbeAutoConfiguration作用：

​	利用EnalbeAutoConfiguration导入一些组件

​	可以查看selectImports()方法的内容

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes);//获取候选配置
```

```java
List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
      getBeanClassLoader());
```

- SpringFactoriesLoader.loadFactoryNames() 扫描所有jar包类路径下 META-INF/spring.factories
- 把扫描到的文件包装成properties对像从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加在容器中