package com.example.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("payment")
public class Payment implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String serial;
}
