package com.forgetting.reminder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.forgetting.reminder.entity.ShiftHandover;

public interface ShiftHandoverService extends IService<ShiftHandover> {
  public void remind(long workDate, boolean flag);
}
