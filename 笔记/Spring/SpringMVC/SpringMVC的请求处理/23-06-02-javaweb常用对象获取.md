# javaweb常用对象获取

## 键值对

如果【实体类1】套【实体类2】，传递参数时使用【实体类2.属性】即可

![1685698517365](image/23-06-02-javaweb常用对象获取/1685698517365.png)

## Json格式

以字符串接收

![1685699838970](image/23-06-02-javaweb常用对象获取/1685699838970.png)

以对象接收

![1685699853590](image/23-06-02-javaweb常用对象获取/1685699853590.png)

或在spring-mvc.xml中加入下面代码，可直接转换为javaBean对象

```xml
<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
    <property name="messageConverters">
      <list>
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"/>
      </list>
    </property>
  </bean>
```

![1685700012037](image/23-06-02-javaweb常用对象获取/1685700012037.png)

## Restful风格

![1685755374990](image/23-06-02-javaweb常用对象获取/1685755374990.png)

### 请求

![1685755450307](image/23-06-02-javaweb常用对象获取/1685755450307.png)

此处仍然存在局限性

![1685755534537](image/23-06-02-javaweb常用对象获取/1685755534537.png)

### 响应

![1685755705942](image/23-06-02-javaweb常用对象获取/1685755705942.png)

### 测试

![1685755808395](image/23-06-02-javaweb常用对象获取/1685755808395.png)
