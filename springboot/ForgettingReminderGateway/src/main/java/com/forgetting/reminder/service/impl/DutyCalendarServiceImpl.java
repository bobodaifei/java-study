package com.forgetting.reminder.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forgetting.reminder.entity.DutyCalendar;
import com.forgetting.reminder.entity.Sms;
import com.forgetting.reminder.entity.Worker;
import com.forgetting.reminder.mapper.DutyCalendarMapper;
import com.forgetting.reminder.service.DutyCalendarService;
import com.forgetting.reminder.utils.CurrencyConstants;
import com.forgetting.reminder.utils.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DutyCalendarServiceImpl extends ServiceImpl<DutyCalendarMapper, DutyCalendar> implements DutyCalendarService {

  private static final Logger logger = LoggerFactory.getLogger(DutyCalendarServiceImpl.class);

  @Autowired
  DutyCalendarMapper dutyCalendarMapper;

  @Autowired
  SmsUtil smsUtil;

  /**
   * @param workDate 将要被提醒工作人的值班日期
   * @param flag     早班还是晚班
   *                 判断将要被提醒的日期的工作人员，早/晚班的人在上班24h前被通知
   */
  @Override
  public void remind(long workDate, boolean flag) {
    logger.info("当前时间为" + new Date() + "处理的是第二天的" + (flag ? "白班" : "夜班") + "人员");

    LambdaQueryWrapper<DutyCalendar> wrapper = new LambdaQueryWrapper<>();
    // 通过被提醒日期条件进行查询(精确匹配）
    wrapper.eq(DutyCalendar::getWorkDate, workDate);
    //获取该日期的数据（包含了早班与晚班人员）
    List<DutyCalendar> list = dutyCalendarMapper.selectList(wrapper);

    //短信集合
    List<Sms> smsList = new ArrayList<>();
    //将要收到短信的人集合
    List<Worker> allWorkers = new ArrayList<>();
    //解析值班人员的信息，获取手机号
    list.forEach(item -> {
      //是否是早/晚班
      allWorkers.addAll(flag ? item.getDayShiftInfo() : item.getNightShiftInfo());
    });
    //放入将要收到短信的人集合
    allWorkers.forEach(worker -> {
      String phone = "13002771758";
      //早晚班不同，提示不同
      String format = DateUtil.format(DateUtil.offsetDay(new Date(), 1), "yyyy年MM月dd日");
      smsList.add(new Sms(phone, CurrencyConstants.TIP1, worker.getName(), format));
    });
    logger.info("将要发送的人员有" + smsList);
    //模拟发短信
    smsUtil.sendSMS(smsList);
  }


}
