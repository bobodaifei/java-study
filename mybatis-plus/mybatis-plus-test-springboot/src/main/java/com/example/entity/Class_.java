package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class_ {
  private String classNo;
  private String className;
  private String leaderTeacher;
  private List<Student> students;;
}
