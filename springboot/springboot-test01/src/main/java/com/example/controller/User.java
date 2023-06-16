package com.example.controller;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {
  @NotNull
  private String name;
  @Max(100)
  @Min(0)
  private Integer age;
}
