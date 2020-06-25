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

### 3、配置文件注入

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

```java
@PropertySource(Value = "classpath:userinfo.properties")
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

