package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Student {
  private String studentNo;
  private String name;
  private String mobile;
  private int gender;
  private int age;
  private String classNo;
}
