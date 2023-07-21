package com.example.demo03.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
  @TableId(value = "id")
  private Integer id;
  private String username;
  private String password;
  private String salt;
  private Integer status;
  @TableLogic
  private Integer deleted;
}