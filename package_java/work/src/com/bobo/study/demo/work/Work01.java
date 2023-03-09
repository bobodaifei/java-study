package com.bobo.study.demo.work;

import java.util.ArrayList;
import java.util.List;

public class Work01 {
  public static void main(String[] args) {
    strToList("name:张三#id:U0001#age:35%name:李四#id:U0002#age:36%name:李四#id:U0002#age:36");
  }

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
    //

    // list.add(null);
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