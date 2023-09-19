package com.forgetting.reminder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forgetting.reminder.entity.DutyCalendar;

public interface DutyCalendarService extends IService<DutyCalendar> {

  public void remind(long workDate, boolean flag);

}
