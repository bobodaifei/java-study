package com.forgetting.reminder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sms {

  private String strMobile;

  private String messageContent;


  public Sms(String strMobile, String tip, String name, String... other) {
    this.strMobile = strMobile;
    this.messageContent = String.format(tip, name, Arrays.toString(other).replaceAll("^\\[|\\]$", ""));
  }

}
