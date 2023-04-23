package com.bobo.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class_ {
  private String classNo;
  private String className;
  private String leaderTeacher;
  private List<Student> students;;
}
