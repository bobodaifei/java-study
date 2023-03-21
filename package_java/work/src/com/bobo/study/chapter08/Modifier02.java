package com.bobo.study.chapter08;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Modifier02 {
  public static void main(String[] args) throws ParseException {
    // Modifier01 modifier01 = new Modifier01();
    // System.out.println(modifier01.n1+modifier01.n2+ modifier01.n3);

    
    String sDate = "2023-3-15 11:25:36";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = simpleDateFormat.parse(sDate);
    System.out.println(date);
    //1900+year
    date.setYear(1000);
    System.out.println(date);

    //获取从1900年到date的时间差
    long time = date.getTime();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    System.out.println(calendar.getTime());
    calendar.add(Calendar.MONTH, 1);
    System.out.println(calendar.getTime());
    // System.out.println(calendar.);

    Date time2 = calendar.getTime();
    long time3 = time2.getTime();

    LocalDate ld = LocalDate.now();
    System.out.println(ld); // 
    System.out.println(ld.getYear());
    System.out.println(ld.getMonth() + " " + ld.getMonthValue());
    System.out.println(ld.getDayOfYear() + " " + ld.getDayOfMonth() + " " + ld.getDayOfWeek());
    LocalDate plusYears = ld.plusYears(1);
    System.out.println(plusYears);
  }
  
}
