package com.bobo.utils;

import org.mapstruct.Named;

public class MapStructUtil {

  @Named("shopCarIdToUserId")
  public static Integer shopCarIdToUserId(String shopCarId) {
    return Integer.valueOf(shopCarId.split(":")[1]);
  }

  @Named("userIdToShopCarId")
  public static String userIdToShopCarId(Integer userId) {
    return "shopCar:" + userId;
  }


}
