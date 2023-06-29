package com.bobo.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

public class WXUtil {
  public static String generatesignature(SortedMap<String, Object> map, String appKey) {
    StringBuilder stringABuilder = new StringBuilder();
    List<String> keys = new ArrayList<String>(map.keySet());
    Collections.sort(keys);
    int index = 0;
    for (int i = 0; i < map.size(); i++) {
      String key = keys.get(i);
      Object value = map.get(key);
      if (StrUtil.isNotEmpty(key) && value != null) {
        stringABuilder.append(index == 0 ? "" : "&").append(key).append("=").append(value);
        index++;
      }
    }
    String stringSignTemp = stringABuilder.toString() + "&key=" + appKey;
    return DigestUtil.md5Hex(stringSignTemp).toUpperCase();
  }
}
