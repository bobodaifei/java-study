package com.bobo.gateway.utils.wxpay;

import com.bobo.gateway.exception.CustomException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class WXpayCommonUtil {

  public static Map<String, String> arryMapToMap(Map<String, String[]> requestParams) {
// 创建一个新的Map对象，用于存储转换后的参数
    Map<String, String> convertedParams = new HashMap<>();

// 遍历原始参数Map
    for (String key : requestParams.keySet()) {
      String[] values = requestParams.get(key);
      if (values != null && values.length > 0) {
        // 将数组中的第一个值取出作为转换后的参数值
        convertedParams.put(key, values[0]);
      }
    }
    return convertedParams;
  }

  public static <T> T xmlToEntity(String xml, Class<T> clazz) throws CustomException {
    JAXBContext jaxbContext = null;
    try {
      jaxbContext = JAXBContext.newInstance(clazz);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      StringReader reader = new StringReader(xml);
      return (T) unmarshaller.unmarshal(reader);
    } catch (Exception e) {
      throw new CustomException("-1","xml转实体类异常");
    }
  }

}
