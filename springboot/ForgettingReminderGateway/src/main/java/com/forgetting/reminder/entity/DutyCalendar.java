package com.forgetting.reminder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.forgetting.reminder.config.WorkerListTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@TableName(value = "z_13bff60790f54a96b91ef4024abd2622", autoResultMap = true)
@Accessors(chain = true)
public class DutyCalendar {

  /**
   * 工作时间
   */
  @TableField("date_760rhzuk")
  private Long workDate;

  /**
   * 白班人员详细信息
   */
  @TableField(value = "member_kvxgb9ry", typeHandler = WorkerListTypeHandler.class)
  private List<Worker> dayShiftInfo;

//  //白班人员详细实体(暂留，可能后续需要)
//  @TableField(exist = false)
//  private List<Worker> dayShiftWorkers;

  /**
   * 白班人员
   */
  @TableField("input_8s872wes")
  private String dayShift;

  /**
   * 夜班人员详细信息
   */
  @TableField(value = "member_bnqthlqd", typeHandler = WorkerListTypeHandler.class)
  private List<Worker> nightShiftInfo;

//  //夜班人员实体
//  @TableField(exist = false)
//  private List<Worker> nightShiftWorkers;

  //夜班人员
  @TableField("input_77rqurd9")
  private String nightShift;

}
