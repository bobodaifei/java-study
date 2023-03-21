package com.bobo.study.work.work4;

import java.util.ArrayList;
import java.util.List;

public class Method01 {
  public static void main(String[] args) {
    // String str1 = "hello";
    // System.out.println(reverse(str1));

    System.out.println(symmetry("1235321"));
    System.out.println(symmetry("12355321"));

    // System.out.println(bigWord("i am big S"));

    // System.out.println(montage("qwertyuiop"));

  }

  // 封装一个方法，能够将输入的String字符串反转 比如 hello 返回 olleh。
  public static String reverse(String str) {
    return new StringBuffer(str).reverse().toString();
  }

  // 二、封装一个方法，判断这个String字符串是不是为对称字符串 比如 aabbaa就是对称字符串。
  public static boolean symmetry(String str) {
    char[] charArray = str.toCharArray();
    for (int i = 0, j = str.length() - 1; i < str.length() / 2; i++, j--) {
      if (charArray[i] != charArray[j]) {
        return false;
      }
    }
    return true;
  }

  // 三、封装一个方法，输入一段英文，能够将单词首字母大写。
  public static String bigWord(String str) {
    String[] split = str.split(" ");
    String str1 = "";
    for (String string : split) {
      String firstStr = string.substring(0, 1).toUpperCase();
      String substring = string.substring(1);
      str1 = str1 + firstStr + substring + " ";
    }
    return str1;
  }

  // 四、封装一个方法、输入一段英文，能够将英文转化为对应的单词儿然后返回英文单词儿的数组。
  public String[] toArry(String str) {
    String[] split = str.split(" ");
    return split;
  }

  // 五、简述一下StringBuilder、StringBuffer、String的区别，哪个是线程安全的，为什么是线程安全的。
  // String:不可变字符序列、效率低、但是复用率高。
  // StringBuffer:可变字符序列、效率较高、线程安全
  // StringBuilder:可变字符序列、效率最高、线程不安全

  // 六、封装一个方法、输入一段字符串，截取他的前四个字符和中间的四个字符，拼成一个字符串，然后返回。
  public static String montage(String str) {
    String str1 = str.substring(0, 4);
    String str2 = str.substring(str.length() / 2 - 2, str.length() / 2 + 2);
    return str1 + str2;
  }
  // 七、简述String str = new String("xx");创建了几个对象。
  // 两个 第一个是创建了在常量池创建了"xx"对象 ，另一个是new String()时在堆内创建的 使变量引用指向堆内对象 再由堆内对象指向常量池对象

  // 有字符串"userNo:u001&userName:张三&password:123456"将该字符串转化并封装为一个user对象
  public static void strToList(String str) {
    String[] arr = str.split("%");
    List<User> list = new ArrayList();
    for (String str1 : arr) {
      String[] arr1 = str1.split("#");
      User user = new User();
      for (String str2 : arr1) {
        String[] arr2 = str2.split(":");
        if (arr2[0].equals("name")) {
          user.setName(arr2[1]);
        }
        if (arr2[0].equals("id")) {
          user.setId(arr2[1]);
        }
        if (arr2[0].equals("age")) {
          user.setAge(Integer.valueOf(arr2[1]));
        }
      }
      if (!(list.indexOf(user) != -1)) {
        list.add(user);
      }
    }
    System.out.println(list);
  }


}

class User {
  private String id;
  private String name;
  private Integer age;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

}
