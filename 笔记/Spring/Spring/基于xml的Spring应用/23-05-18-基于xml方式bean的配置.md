# SpringBean的配置详解

ApplicationContext的配置

![1684329389520](image/23-05-18-SpringBean的配置详解/1684329389520.png)

## Bean的id和全限定名

beanName的存储

![1684371394654](image/23-05-18-SpringBean的配置详解/1684371394654.png)

### 配置id的bean

![1684371656119](image/23-05-18-SpringBean的配置详解/1684371656119.png)

### 未配置id的bean

依据全限定名

![1684371735879](image/23-05-18-SpringBean的配置详解/1684371735879.png)

## Bean的别名配置

![1684371858237](image/23-05-18-SpringBean的配置详解/1684371858237.png)

### 配置id

存于applicationcontext下的aliasMap映射

![1684371981485](image/23-05-18-SpringBean的配置详解/1684371981485.png)

### 未配置id

如果未配置id而配置别名，则别名的第一个就是beanName

![1684372111665](image/23-05-18-SpringBean的配置详解/1684372111665.png)

## Bean的作用范围

原生环境下的Spring的bean作用范围

![1684372200326](image/23-05-18-SpringBean的配置详解/1684372200326.png)

webmvc环境下的Spring的bean作用范围

![1684372918928](image/23-05-18-SpringBean的配置详解/1684372918928.png)

## Bean的延迟加载

类似懒汉式，跟beanFactory的形式

![1684372951107](image/23-05-18-SpringBean的配置详解/1684372951107.png)

## Bean初始化方法和销毁方法配置

![1684373139515](image/23-05-18-SpringBean的配置详解/1684373139515.png)

销毁需要显式的销毁

![1684373384249](image/23-05-18-SpringBean的配置详解/1684373384249.png)

InitializingBean(初始化操作，但时机要早一步)

![1684374617094](image/23-05-18-SpringBean的配置详解/1684374617094.png)

![1684374846870](image/23-05-18-SpringBean的配置详解/1684374846870.png)

## Bean实例化配置

![1684374907610](image/23-05-18-SpringBean的配置详解/1684374907610.png)

### 构造方法实例化

constructor-arg：代表的是构造参数，而不是构造方法的参数

![1684375183467](image/23-05-18-SpringBean的配置详解/1684375183467.png)

### 工厂方式实例化

![1684375653589](image/23-05-18-SpringBean的配置详解/1684375653589.png)

#### 静态工厂方法实例化Bean

可以做额外操作，或第三方Bean。

![1684375878544](image/23-05-18-SpringBean的配置详解/1684375878544.png)

无参

![1684375910269](image/23-05-18-SpringBean的配置详解/1684375910269.png)

有参

![1684379002019](image/23-05-18-SpringBean的配置详解/1684379002019.png)

#### 实例工厂方法实例化Bean

(可以做额外操作，或第三方Bean)

![1684378618557](image/23-05-18-SpringBean的配置详解/1684378618557.png)

无参

![1684378520005](image/23-05-18-SpringBean的配置详解/1684378520005.png)

有参

![1684379068014](image/23-05-18-SpringBean的配置详解/1684379068014.png)

#### 实现FactoryBean规范延迟实例化Bean

上方的factory-method为此处的getObject()，如果实现了FactoryBean则不需要特别指明factory-method指向的方法，且只有getBean才加载实例

![1684379317078](image/23-05-18-SpringBean的配置详解/1684379317078.png)

实现类

![1684379651369](image/23-05-18-SpringBean的配置详解/1684379651369.png)

![1684379702083](image/23-05-18-SpringBean的配置详解/1684379702083.png)

#### 工厂方式实例化区别

前两种

![1684379947598](https://file+.vscode-resource.vscode-cdn.net/e%3A/Visual%20Studio%20Code/java/java_study/%E7%AC%94%E8%AE%B0/Spring/Spring/%E5%9F%BA%E4%BA%8Exml%E7%9A%84Spring%E5%BA%94%E7%94%A8/image/23-05-18-SpringBean%E7%9A%84%E9%85%8D%E7%BD%AE%E8%AF%A6%E8%A7%A3/1684379947598.png)

实现FactoryBean规范

![1684379976032](image/23-05-18-SpringBean的配置详解/1684379976032.png)

真正的缓存区域，且是延迟加载的（加载配置文件时加载FactoryBean实例，但只有在getBean时才真正调用getObject获取实例且存入缓存，以后再次调用就去缓存中取）

![1684380096225](image/23-05-18-SpringBean的配置详解/1684380096225.png)

## Bean的依赖注入方式

![1684380678572](image/23-05-18-SpringBean的配置详解/1684380678572.png)

![1684380956021](image/23-05-18-SpringBean的配置详解/1684380956021.png)

### setter

#### List

![1684381953315](image/23-05-18-SpringBean的配置详解/1684381953315.png)

注入bean方式1(以下省略)

![1684381978614](image/23-05-18-SpringBean的配置详解/1684381978614.png)

注入bean方式2

![1684382020868](image/23-05-18-SpringBean的配置详解/1684382020868.png)

#### Set

![1684382116280](image/23-05-18-SpringBean的配置详解/1684382116280.png)

注入bean方式

![1684382230631](image/23-05-18-SpringBean的配置详解/1684382230631.png)

#### Map

![1684388316122](image/23-05-18-SpringBean的配置详解/1684388316122.png)

注入bean方式

![1684388378443](image/23-05-18-SpringBean的配置详解/1684388378443.png)

#### Properties

![1684389172436](image/23-05-18-SpringBean的配置详解/1684389172436.png)

注入bean方式

![1684389201749](image/23-05-18-SpringBean的配置详解/1684389201749.png)

### 扩展：自动装配方式

![1684389261712](image/23-05-18-SpringBean的配置详解/1684389261712.png)

## Spring的其他配置标签

![1684389501674](image/23-05-18-SpringBean的配置详解/1684389501674.png)

### 默认标签

![1684389723120](image/23-05-18-SpringBean的配置详解/1684389723120.png)

#### beans

![1684391073846](image/23-05-18-SpringBean的配置详解/1684391073846.png)

环境设置

![1684391216561](image/23-05-18-SpringBean的配置详解/1684391216561.png)

如果在加载xml文件未指明环境，则会使用【默认环境】，如果需要使用上上图的方案激活指定环境，注：默认环境属于公共部分

#### import

![1684392229150](image/23-05-18-SpringBean的配置详解/1684392229150.png)

#### alias

![1684392451948](image/23-05-18-SpringBean的配置详解/1684392451948.png)

### 自定义标签

![1684393220760](image/23-05-18-SpringBean的配置详解/1684393220760.png)

![1684389664687](image/23-05-18-SpringBean的配置详解/1684389664687.png)

![1684389684482](image/23-05-18-SpringBean的配置详解/1684389684482.png)
