package org.example.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class_ {
  private String class_no;
  private String class_name;
  private String leader_teacher;
  private List<Student> students;;
}
