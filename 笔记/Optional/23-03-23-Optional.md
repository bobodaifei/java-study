# Optional

## 概述

我们在编写代码的时候出现最多的就是空指针异常。所以在很多情况下我们需要做各种非空的判断。

尤其是对象中的属性还是一个对象的情况下。这种判断会更多。而过多的判断语句会让我们的代码显得臃肿不堪。

所以在JDK8中引入了Optional，养成使用Optional的习惯后你可以写出更优雅的代码来避免空指针异常。

并且在很多函数式编程相关的API中也都用到了Optional，如果不会使用Optional也会对函数式编程的学习造成影响。

## 使用

### 创建对象

Optional就好像是包装类，可以把我们的具体数据封装Optional对象内部。然后我们去使用Optional中封装好的方法操作封装进去的数据就可以非常优雅的避免空指针异常。I

我们一般使用Optional的静态方法ofNullable来把数据封装成一个Optional对象。无论传入的参数是否为null都不会出现问题。

```java
Author author = getAuthors();
Optional<Object> ofNullable = Optional.ofNullable(author);
```

***你可能会觉得还要加一行代码来封装数据比较麻烦。但是如果改造下getAuthor方法，让其的返回值就是封装好的Optional的话，我们在使用时就会方便很多。

而且在实际开发中我们的数据很多是从数据库获取的。Mybatis从3.5版本可以也已经支持Optional了。我们可以直接把dao方法的返回值类型定义成optional类型，MyBastis会自己把数据封装成optional对象返回。封装的过程也不需要我们自己操作。

```java
private static Optional<Author> getAuthorOptional() {
    Author author2 = new Author(2L, "tom2", 19, "asdasd", null);
    return Optional.ofNullable(author2);
}

Optional<Author> ofNullable1 = getAuthorOptional();
ofNullable1.ifPresent(author1 -> System.out.println(author1.getName()));
```

如果你确定一个对象不是空的，则可以使用Optional的静态方法of来把数据封装成Optional对象。但是一定要注意，如果使用of的时候传入的参数必须不为null。

```java
Optional<Author> ofNullable2 = Optional.of(author);
ofNullable2.ifPresent(author1 -> System.out.println(author1.getName()));
```

如果一个方法的返回值类型是Optional类型。而如果我们经判断发现某次计算得到的返回值为null，这个时候就需要把null封装成Optional对象返回。这时则可以使用Optional的静态方法empty来进行封装。但其实Optional.ofNullable()就等价于下面的操作

```java
private static Optional<Author> getAuthorOptional01() {
    Author author2 = new Author(2L, "tom2", 19, "asdasd", null);
    return author2 == null ? Optional.empty() : Optional.of(author2);
}
```

### 安全使用

我们获取到一个Optional对象后肯定需要对其中的数据进行使用。这时候我们可以使用其**ifpresent**方法对来消费其中的值。

这个方法会判断其内封装的数据是否为空，不为空时才会执行具体的消费代码。这样使用起来就更加安全了。

例如,以下写法就优雅的避免了空指针异常。

```java
Optional<Author> ofNullable1 = getAuthorOptional();
ofNullable1.ifPresent(author1 -> System.out.println(author1.getName()));
```

### 获取值

不安全

如果我们想获取值自己进行处理可以使用get方法获取，但是不推荐。因为当Optional内部的数据为空的时候会出现异常。

安全

```java
// 如果为空则返回一个默认值
Author author3 = ofNullable1.orElseGet(() -> new Author());

// 如果为空则根据传入参数获取异常
try {
      Author author4 = ofNullable1.orElseThrow(() -> new RuntimeException("空"));
      System.out.println(author4.getName());
} catch (Throwable e) {
      // TODO: handle exception
}
```

### 过滤

我们可以使用filter方法对数据进行过滤。如果原本是有数据的，但是不符合判断，也会变成一个无数据的Optional对象。

```java
Optional<Author> ofNullable = getAuthorOptional();
ofNullable.filter(author -> author.getAge() > 18).ifPresent(author -> System.out.println(author.getName()));
```

### 判断

我们可以使用isPresent方法进行是否存在数据的判断。如果为空返回值为false,如果不为空，返回值为true。但是这种方式并不能体现Optional的好处，更推荐使用ifPresent方法。

```java
Optional<Author> ofNullable = getAuthorOptional();

// ofNullable.filter(author -> author.getAge() > 18).ifPresent(author -> System.out.println(author.getName()));

if (ofNullable.isPresent()) {
  System.out.println(ofNullable.get().getName());
}
```

### 数据转换

Optional还提供了map可以让我们的对数据进行转换，并且转换得到的数据也还是被Optional包装好的，保证了我们的使用安全。例如我们想获取作家的书籍集合。

```java
Optional<Author> ofNullable = getAuthorOptional();
ofNullable.map((author) -> author.getBooks()).ifPresent(books -> {
  books.forEach(book -> System.out.println(book.getName()));
});
```
