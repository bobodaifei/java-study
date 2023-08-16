package com.bobo.gateway.utils.wxpay;

import cn.hutool.core.util.StrUtil;
import com.bobo.gateway.exception.CustomException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.*;

//加签预备，按字典排序
public class WXpaySignature {
  public static String getSignatureContent(RequestParametersHolder requestHolder, String appKey) {
    return getSignContent(getSortedMap(requestHolder), appKey);
  }

  public static Map<String, String> getSortedMap(RequestParametersHolder requestHolder) {
    Map<String, String> sortedParams = new TreeMap<String, String>();
    WXpayHashMap appParams = requestHolder.getApplicationParams();
    if (appParams != null && appParams.size() > 0) {
      sortedParams.putAll(appParams);
    }
    WXpayHashMap protocalMustParams = requestHolder.getProtocalMustParams();
    if (protocalMustParams != null && protocalMustParams.size() > 0) {
      sortedParams.putAll(protocalMustParams);
    }
    return sortedParams;
  }

  public static String getSignContent(Map<String, String> sortedParams, String appKey) {
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
    content.append("&").append("key=").append(appKey);
    return content.toString();
  }

  /**
   * 判断签名是否正确，必须包含sign字段，否则返回false。
   *
   * @param xmlStr XML格式数据
   * @param key    API密钥
   * @return
   * @throws Exception
   */
  public static boolean isSignatureValid(String xmlStr, String key) throws CustomException {
    try {
      Map<String, String> data = rec(xmlStr);
      if (!data.containsKey("sign")) {
        return false;
      }
      String sign = data.get("sign");
      return generateSignature(data, key).equals(sign);
    } catch (Exception e) {
      throw new CustomException("-1", "未知原因，验签失败");
    }
  }

  /**
   * 将xml转化map
   *
   * @param xml
   * @return
   */
  public static Map<String, String> rec(String xml) {
    Map<String, String> map = new TreeMap<>();
    Document doc = null;
    try {
      doc = DocumentHelper.parseText(xml);
      Element rootElement = doc.getRootElement();//获取根节点
      Iterator<Element> iterator = rootElement.elementIterator();
      while (iterator.hasNext()) {
        Element next = iterator.next();
        String name = next.getName();
        String stringValue = next.getStringValue();
        map.put(name, stringValue);
      }
    } catch (DocumentException e) {
      throw new RuntimeException(e);
    }
    return map;
  }

  /**
   * 生成签名
   *
   * @param data 待签名数据
   * @param key  API密钥
   * @return
   * @throws Exception
   */
  public static String generateSignature(final Map<String, String> data, String key) throws Exception {
    return generateSignature(data, key, "MD5");
  }

  /**
   * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
   *
   * @param data     待签名数据
   * @param key      API密钥
   * @param signType 签名方式
   * @return
   * @throws Exception
   */
  public static String generateSignature(final Map<String, String> data, String key, String signType) throws Exception {
    Set<String> keySet = data.keySet();
    String[] keyArray = keySet.toArray(new String[keySet.size()]);
    Arrays.sort(keyArray);
    StringBuilder sb = new StringBuilder();
    for (String k : keyArray) {
      if (k.equals("sign")) {
        continue;
      }
      if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
        sb.append(k).append("=").append(data.get(k).trim()).append("&");
    }
    sb.append("key=").append(key);
    if ("MD5".equals(signType)) {
      return MD5(sb.toString()).toUpperCase();
    } else if ("HMAC-SHA256".equals(signType)) {
      return HMACSHA256(sb.toString(), key);
    } else {
      throw new Exception(String.format("Invalid sign_type: %s", signType));
    }
  }

  /**
   * 生成 MD5
   *
   * @param data 待处理数据
   * @return
   * @throws Exception
   */
  public static String MD5(String data) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] array = md.digest(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }

  /**
   * 生成 HMACSHA256
   *
   * @param data 待处理数据
   * @param key  密钥
   * @return
   * @throws Exception
   */
  public static String HMACSHA256(String data, String key) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
    sha256_HMAC.init(secret_key);
    byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
    StringBuilder sb = new StringBuilder();
    for (byte item : array) {
      sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
    }
    return sb.toString().toUpperCase();
  }


}
