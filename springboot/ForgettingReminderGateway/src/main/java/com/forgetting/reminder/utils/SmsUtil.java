package com.forgetting.reminder.utils;

import com.forgetting.reminder.config.SmsConfig;
import com.forgetting.reminder.entity.Sms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class SmsUtil {

  private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  SmsConfig smsConfig;

  static HttpHeaders headers = new HttpHeaders();

  static {
    //    headers.setHost(new InetSocketAddress("jhoasms.qd.gov.cn", 8099));
    headers.setContentType(MediaType.TEXT_XML);
    headers.set("SOAPAction", "\"http://tempuri.org/SendMessageData\"");
  }

  /**
   * @param list 包含了将要发送到的手机号和内容
   */
  public void sendSMS(List<Sms> list) {
    list.forEach(item -> {
      StringBuilder builder = new StringBuilder();
      String xmlData = builder.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                      "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http:/www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                      "<soap:Body>\n" +
                      "<SendMessageData xmlns=\"http://tempuri.org/\">\n")
              .append("<strMobile>").append(item.getStrMobile()).append("</strMobile>\n")
              .append("<messageContent>").append(item.getMessageContent()).append("</messageContent>\n")
              .append("<sendKey>").append(smsConfig.getSendKey()).append("</sendKey>\n")
              .append("<orgCode>").append(smsConfig.getOrgCode()).append("</orgCode>\n")
              .append("<checkCode>").append(smsConfig.getCheckCode()).append("</checkCode>\n")
              .append("<sendName>").append(smsConfig.getSendName()).append("</sendName>\n")
              .append("</SendMessageData>\n" +
                      "</soap:Body>\n" +
                      "</soap:Envelope>\n").toString();
      System.out.println(xmlData);
//      HttpEntity<String> requestEntity = new HttpEntity<>(xmlData, headers);
//      try {
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(smsConfig.getUrl(), requestEntity, String.class);
//        logger.info(item.getStrMobile() + "短信发送成功");
//        logger.info(responseEntity.getBody());
//        System.out.println(responseEntity.getBody());
//      } catch (Exception e) {
//        logger.error("为" + item.getStrMobile() + "发送短信失败,失败原因" + e.getMessage());
//      }
    });
  }

}
