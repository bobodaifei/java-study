package com.forgetting.reminder.tasks;

import cn.hutool.core.date.DateUtil;
import com.forgetting.reminder.service.DutyCalendarService;
import com.forgetting.reminder.service.ShiftHandoverService;
import com.forgetting.reminder.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduling {

  private static final Logger logger = LoggerFactory.getLogger(Scheduling.class);

  @Autowired
  DutyCalendarService dutyCalendarService;

  /**
   * 每天9/18点提醒第二天9/18点上班的人
   */
//  @Scheduled(cron = "0 0 9,18 * * ?")
  //测试用
//  @Scheduled(cron = "*/30 * * * * ?")
  public void remindScheduled() {
    logger.info("上班提醒执行开始");
    // 利用工具类获取明天的日期（年月日）
    // 如果后期时间改动成成范围的可改这个
    long workDate = DateUtils.addOffsetToCurrentDate(60 * 24);
    //是否是早班
    boolean flag = DateUtil.thisHour(true) <= 12;
    dutyCalendarService.remind(workDate, flag);
    logger.info("上班提醒执行结束");
  }

  @Autowired
  ShiftHandoverService shiftHandoverService;

  /**
   * 每天9/18点提醒当天值班但未交接的人
   */
//  @Scheduled(cron = "0 30 9,18 * * ?")
  @Scheduled(cron = "*/30 * * * * ?")
  public void remindScheduled1() {
    logger.info("接班提醒执行开始");
    //利用工具类获取当前的日期（年月日）
    long workDate = DateUtils.addOffsetToCurrentDate(0);
    //是否是早班下班
    boolean flag = DateUtil.thisHour(true) > 12;
    shiftHandoverService.remind(workDate, flag);
    logger.info("接班提醒执行结束");
  }
}
