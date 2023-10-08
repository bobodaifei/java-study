package com.forgetting.reminder.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtils {

  public static long addOffsetToCurrentDate(int offsetMinutes) {
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime result = currentTime.plusMinutes(offsetMinutes);
    return result.toLocalDate().atStartOfDay().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
  }

}
