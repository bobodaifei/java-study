package com.forgetting.reminder.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.forgetting.reminder.config.WorkerListTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@TableName(value = "z_7f2b6c6c2a384af78acf9121d2e6066c", autoResultMap = true)
@Accessors(chain = true)
public class ShiftHandover {

  /**
   * 值班时间
   */
  @TableField("date_u0tkztvc")
  private long dutyDate;

  /**
   * 交班人员信息
   */
  @TableField(value = "member_d78m6upa", typeHandler = WorkerListTypeHandler.class)
  private List<Worker> shiftHandoverInfo;

  /**
   * 接班人员信息
   */
  @TableField(value = "member_itxqrxad", typeHandler = WorkerListTypeHandler.class)
  private List<Worker> successionInfo;

  /**
   * 班次
   */
  @TableField("select_152ux0x5")
  private String workeRange;


}
