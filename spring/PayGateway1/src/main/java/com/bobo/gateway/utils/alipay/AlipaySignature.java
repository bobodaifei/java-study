package com.bobo.gateway.utils.alipay;

import cn.hutool.core.util.StrUtil;
import com.bobo.gateway.exception.CustomException;

import java.util.*;

//加签预备，按字典排序
public class AlipaySignature {
  public static String getSignatureContent(RequestParametersHolder requestHolder) {
    return getSignContent(getSortedMap(requestHolder));
  }

  public static Map<String, String> getSortedMap(RequestParametersHolder requestHolder) {
    Map<String, String> sortedParams = new TreeMap<String, String>();
    AlipayHashMap appParams = requestHolder.getApplicationParams();
    if (appParams != null && appParams.size() > 0) {
      sortedParams.putAll(appParams);
    }
    AlipayHashMap protocalMustParams = requestHolder.getProtocalMustParams();
    if (protocalMustParams != null && protocalMustParams.size() > 0) {
      sortedParams.putAll(protocalMustParams);
    }
    AlipayHashMap protocalOptParams = requestHolder.getProtocalOptParams();
    if (protocalOptParams != null && protocalOptParams.size() > 0) {
      sortedParams.putAll(protocalOptParams);
    }

    return sortedParams;
  }

  public static String getSignContent(Map<String, String> sortedParams) {
    StringBuilder content = new StringBuilder();
    List<String> keys = new ArrayList<String>(sortedParams.keySet());
    Collections.sort(keys);
    int index = 0;
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = sortedParams.get(key);
      if (StrUtil.isAllNotEmpty(key, value)) {
        content.append(index == 0 ? "" : "&").append(key).append("=").append(value);
        index++;
      }
    }
    return content.toString();
  }

  public static boolean rsaCheckV2(Map<String, String> params, String publicKey,
                                   String charset) throws CustomException {
    String sign = params.get("sign");
    String content = getSignCheckContentV2(params);
    return AlipaySignUtils.rsa2Verify(content, publicKey, charset, sign);
  }

  public static String getSignCheckContentV2(Map<String, String> params) {
    if (params == null) {
      return null;
    }

    params.remove("sign");
    params.remove("sign_type");

    StringBuilder content = new StringBuilder();
    List<String> keys = new ArrayList<String>(params.keySet());
    Collections.sort(keys);

    int index = 0;
    for (int i = 0; i < keys.size(); i++) {
      String key = keys.get(i);
      String value = params.get(key);
      if (StrUtil.isAllNotEmpty(key, value)) {
        content.append((index == 0 ? "" : "&") + key + "=" + value);
        index++;
      }
    }

    return content.toString();
  }


}
