package com.example.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class StudentVO implements Serializable {

  @ApiModelProperty("学生编号")
  @TableId(value = "student_no", type = IdType.ASSIGN_ID)
  private String studentNo;

  @ApiModelProperty("学生姓名")
  private String name;

  @ApiModelProperty("电话号码")
  private String mobile;

  @ApiModelProperty("性别")
  private Integer gender;

  @ApiModelProperty("年龄")
  private Integer age;

  @ApiModelProperty("班级编号")
  private String classNo;
}

