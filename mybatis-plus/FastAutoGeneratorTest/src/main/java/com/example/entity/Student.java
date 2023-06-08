package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("student")
@ApiModel(value = "Student对象", description = "")
public class Student implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("学生编号")
  @TableId("student_no")
  private String studentNo;

  @ApiModelProperty("学生姓名")
  @TableField("name")
  private String name;

  @ApiModelProperty("电话号码")
  @TableField("mobile")
  private String mobile;

  @ApiModelProperty("性别")
  @TableField("gender")
  private Integer gender;

  @ApiModelProperty("年龄")
  @TableField("age")
  private Integer age;

  @ApiModelProperty("班级编号")
  @TableField("class_no")
  private String classNo;
}

