package com.bobo.purview.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("emp")
public class Emp {

  @TableId(type = IdType.AUTO)
  private String empno;
  private String ename;
  private String mgr;
  private String level;
  private String job;

  @TableField(exist = false)
  private List<Emp> children;

}
