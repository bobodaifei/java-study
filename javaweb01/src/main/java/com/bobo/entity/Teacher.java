package com.bobo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teacher {
  private String teacherNo;
  private String teacherName;
  private int age;
  private int gender;
  private String pwd;
  private Class_ class_;

  
}
