# ActiveRecord

ActiveRecord（简称AR）一直广受动态语言（ PHP 、 Ruby 等）的喜爱，而 Java 作为准静态语言，对于ActiveRecord 往往只能感叹其优雅，所以我们也在 AR 道路上进行了一定的探索，喜欢大家能够喜欢。

什么是ActiveRecord？

ActiveRecord也属于ORM（对象关系映射）层，由Rails最早提出，遵循标准的ORM模型：表映射到记录，记录映射到对象，字段映射到对象属性。配合遵循的命名和配置惯例，能够很大程度的快速实现模型的操作，而且简洁易懂。

ActiveRecord的主要思想是：

* 每一个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录；通常表的每个字段在类中都有相应的Field；
* ActiveRecord同时负责把自己持久化，在ActiveRecord中封装了对数据库的访问，即CURD；
* ActiveRecord是一种领域模型(Domain Model)，封装了部分业务逻辑；

## 开启AR之旅

在MP中，开启AR非常简单，只需要将实体对象继承Model即可。

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("student")
public class Student extends Model<Student> {
  @TableId(type = IdType.AUTO)
  private String studentNo;
  private String name;
  private String mobile;
  private int gender;
  private int age;
  private String classNo;
}
```

## 根据主键查询

```java
  @Test
  public void testSelect() {
    Student student = new Student();
    student.setStudentNo("123");
    Student student1 = student.selectById();
    System.out.println(student1);
  }
```

## 新增数据

```java
  @Test
  public void testAR() {
    User user = new User();
    user.setName("刘备");
    user.setAge(30);
    user.setPassword("123456");
    user.setUserName("liubei");
    user.setEmail("liubei@itcast.cn");
    boolean insert = user.insert();
    System.out.println(insert);
  }
```

## 更新操作

```java
  @Test
  public void testAR() {
    User user = new User();
    user.setId(8L);
    user.setAge(35);
    boolean update = user.updateById();
    System.out.println(update);
  }
```

## 删除操作

```java
  @Test
  public void testAR() {
    User user = new User();
    user.setId(7L);
    boolean delete = user.deleteById();
    System.out.println(delete);
  }
```

## 根据条件查询

```java
  @Test
  public void testAR() {
    User user = new User();
    QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
    userQueryWrapper.le("age","20");
    List<User> users = user.selectList(userQueryWrapper);
    for (User user1 : users) {
      System.out.println(user1);
    }
  }
```
