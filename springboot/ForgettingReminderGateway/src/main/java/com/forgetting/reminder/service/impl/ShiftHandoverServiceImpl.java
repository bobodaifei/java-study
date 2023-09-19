package com.forgetting.reminder.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.forgetting.reminder.entity.DutyCalendar;
import com.forgetting.reminder.entity.ShiftHandover;
import com.forgetting.reminder.entity.Sms;
import com.forgetting.reminder.entity.Worker;
import com.forgetting.reminder.mapper.ShiftHandoverMapper;
import com.forgetting.reminder.service.DutyCalendarService;
import com.forgetting.reminder.service.ShiftHandoverService;
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
public class ShiftHandoverServiceImpl extends ServiceImpl<ShiftHandoverMapper, ShiftHandover> implements ShiftHandoverService {

    private static final Logger logger = LoggerFactory.getLogger(ShiftHandoverServiceImpl.class);

    @Autowired
    DutyCalendarService dutyCalendarService;

    @Autowired
    ShiftHandoverMapper shiftHandoverMapper;

    @Autowired
    SmsUtil smsUtil;


    /**
     * @param workDate 将要被提醒工作人的接班日期
     * @param flag     早班还是晚班
     *                 如果是早班，则先查找当天值班表，再判断接班表是否存在
     *                 如果是晚班，则先查找前天值班表，再判断接班表是否存在
     */
    @Override
    public void remind(long workDate, boolean flag) {
        logger.info("当前时间为" + new Date() + "处理的是半小时前未交班的" + (flag ? "白班" : "夜班") + "人员");
        LambdaQueryWrapper<DutyCalendar> wrapper = new LambdaQueryWrapper<>();
        //晚班的值班开始日期
        long lastWorkDate = workDate - (24 * 60 * 60 * 1000);
        //通过被提醒日期条件进行查询（白班与夜班不同）
        wrapper.eq(DutyCalendar::getWorkDate, flag ? workDate : lastWorkDate);
        List<DutyCalendar> dutyCalendarList = dutyCalendarService.list(wrapper);

        // 查询未接班的人员，无法直接查，数据库中保存为了json
        LambdaQueryWrapper<ShiftHandover> wrapper1 = new LambdaQueryWrapper<>();
        //条件1: 接班表内值班日期（夜班与白班不同）
        //条件2：班次正确
        wrapper1.ge(ShiftHandover::getDutyDate, flag ? workDate : lastWorkDate)
                .lt(ShiftHandover::getDutyDate, (flag ? workDate : lastWorkDate) + (24 * 60 * 60 * 1000))
                .eq(ShiftHandover::getWorkeRange, flag ? CurrencyConstants.DAY_SHIFT : CurrencyConstants.NIGHT_SHIFT);
        List<ShiftHandover> shiftHandovers = shiftHandoverMapper.selectList(wrapper1);

        //已经下班列表
        List<Worker> allWorkers = new ArrayList<>();
        //下班且交班列表
        List<Worker> incompleteWorkers = new ArrayList<>();

        //已经下班列表(需要考虑白班夜班)
        dutyCalendarList.forEach(item -> {
            allWorkers.addAll(flag ? item.getDayShiftInfo() : item.getNightShiftInfo());
        });

        //只需考虑交班人员
        shiftHandovers.forEach(item -> {
            incompleteWorkers.addAll(item.getShiftHandoverInfo());
        });

        //取差集
        List<Worker> difference = new ArrayList<>(allWorkers);
        difference.removeAll(incompleteWorkers);

        //短信集合
        List<Sms> smsList = new ArrayList<>();
        //放入将要收到短信的人集合
        difference.forEach(worker -> {
            String phone = "13002771758";
            smsList.add(new Sms(phone, CurrencyConstants.TIP2, worker.getName()));
        });
        //模拟发短信
        smsUtil.sendSMS(smsList);
    }
}
