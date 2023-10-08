package com.bobo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.bobo.component.convert.AliPayConvert;
import com.bobo.entity.AliPay;
import com.bobo.pojo.dto.AliPayDTO;
import com.bobo.pojo.vo.AliPayVO;
import com.bobo.service.AliPayService;
import com.bobo.service.OrderService;
import com.bobo.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AliPayServiceImpl implements AliPayService {

  private final AliPayConvert INSTANCE = AliPayConvert.INSTANCE;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  AlipayClient alipayClient;

  @Autowired
  AlipayConfig alipayConfig;

  @Autowired
  OrderService orderService;

  @Autowired
  JedisUtil jedisUtil;

//  @Override
//  public String pay(AliPayDTO dto) throws AlipayApiException {
//    AliPay aliPay = INSTANCE.toEntity(dto);
//    aliPay.setProduct_code("FAST_INSTANT_TRADE_PAY");
//    String bizContent = JSON.toJSONString(aliPay);
//
//    AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
//    request.setNotifyUrl(notifyUrl);
//    request.setReturnUrl(returnUrl);
//    request.setBizContent(bizContent);
//
//    AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
//    if (response.isSuccess()) {
//      System.out.println(response.getBody());
//      System.out.println("调用成功");
//    } else {
//      System.out.println("调用失败");
//    }
//    return response.getBody();
//  }

  @Override
  public String pay(AliPayDTO dto) throws AlipayApiException {
//    String url = "http://127.0.0.1:8080/alipay/pay";
    String url = "http://m.jnbat.com:8081/PayGateway/alipay/pay";
    AliPay aliPay = INSTANCE.toEntity(dto);
    aliPay.setNotify_url(dto.getNotifyUrl());
    aliPay.setReturn_url(dto.getReturnUrl());
    String bizContent = JSON.toJSONString(aliPay);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<String> requestEntity = new HttpEntity<>(bizContent, headers);
    ResponseEntity<AliPayVO> responseEntity = restTemplate.postForEntity(url, requestEntity, AliPayVO.class);
    AliPayVO aliPayVO = responseEntity.getBody();
    assert aliPayVO != null;
    if ("200".equals(aliPayVO.getCode())) {
      System.out.println(aliPayVO.getData());
      System.out.println("调用成功");
    } else {
      System.out.println("调用失败");
    }
    return aliPayVO.getData();
  }

//  public static void form1() throws AlipayApiException {
//    String url = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
////    String url = "https://openapi.alipaydev.com/gateway.do";
////    String url = "http://47.94.220.67:10001/aliPcPay";
//
//    String app_id = "2021000122664646";
//    String method = "alipay.trade.page.pay";
//    String format = "json";
//    String charset = "UTF-8";
//    String sign_type = "RSA2";
//    String version = "1.0";
//    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String timestamp = dateFormat.format(new Date());
//
//    String private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJ7iUYIKSS3gt77+gwoU//fY0t+sI3YIphtH82fba6r2PWLOQ1M3EiiZCGJHJ9LoL2MPWXcX+GdlJeZuxxWS08IC8cPMpCMQs3IyM22BuJ9Zj8wpzyWNAL+lWdWszHsTusCkvXjf58DkxBGPAiZxr+ZOOrh66doFInoWV34WZjxZQ3Q/kRYGvjnbAn1+yWoHthPOV8Jai7KxY0o39MNya6aCvPSidA6YQtVo8qXecM0idjaPp16qit9g5vqMIJ1XRxVL+8hNqZ7NQO31K6Fo3s2pwTSKl0pU4eZZYDTDDAiBdBSh0VXrbZY+NUT3RY6iJ4JgopNen4fvlmU41EFzlPAgMBAAECggEAJvIoZTSqY5Hw+jDWhgy2cvS3jXkVp99Jk6cMmI1MsE/fx9TrJx58Xn0rYsUd0rz0W6GdZwK0OHpmbaNwNd0xSZ3oz3dWwPPqL8prIgxJUl4XsZ8eA8wSCp/H1uORmP0xVTWfDqFYbxz2aRcqjvxAAf7ozkFEi9t99xS6euwZJihw6iWWrusV6xy+e1fKo5V9pQvXRKDnDSHUoHB8u7WlrPRQiYDD/JBhBsauWdMinbvx7nZ33H4939lIVeEHI9avyvWUo/Xx2LT1fSr4P8h09Y3IHM+WnetY1bljZKForiCfKUmHX0CBQCxbCsgWJN5T8/iIaoR7p70+AHQTdP2T4QKBgQD1rsbU/Am9CfcIjeIdvdQnIQdwTxHAd/roW3ioHWzb7vrpMM6xuvgYKp1Wlnbn8SsKLu2qZ6LpQZWSxZb/EAOwwKks8X6u6NHGVXpj2GbWAE+3OW1GgSZ92Qfzz7O0rBKU0ygNetkIZt6B1Owo9WEph8Pn2j7mjmR/yMOs6m0ckQKBgQCPuPhrQLYyJZrYkqp0M15DCKT0ygXz/kg9a/klZeZl0XHcNS/OtQPzkZ62jtGIonVUbNKLyr2jCNafrBkUYKaQzKM5IHSVUep6uKebk3jz7B4GMmKxImvPFIrJVoQBhCa/ezlx/qe01E5N6BispKVHikt36cUVbGMwz5I6uj1n3wKBgQCVjKdBvI3dAXTbQyqx+fxrOSLDDY57v0z/JbcDdq3uDsGzMt+OtoYQh9gwffSxaU9OqpRHV0CGhkKbDiXE6yWj8RLJ5dK8e8I93YhbwL2QZK4hlRZchX89ud+qXPNB9kB1UTefRPlZsOye0VJT+FIFqrvopSQGygSI+FeMKULz0QKBgFWWnbmhS98JLfGTRhb1EElzOGjedjUbVQHe7vwqds5jsB6nPdUnTzcZnR6yZm2r8ZtZQvkkv1H7zXRBFJ5iAE9yzjES9tqFcdGrbYTkib8Y+bhPAR7jlLg5T30fHHWasUiOpWMRd+XkGM2kTi1hz1uPK+o5OLjNVzSWKEr45eCZAoGAZRI7GnbVhP61zqlOOIBsYCT8F4UAMWGP5ZTpttxU1CF5veAz6EOYMz3nIh5JDYBkFEagmAiuii5Y6JrYTjJIyTHjv0TLqhUZcxjpXhDLkptaGVofMHD7odG7bGfD+0JMDZNQALyA67BZ941tp0nNbjCguoqH5pA3n7RqUOW5/6Y=";
//
//    AliPay aliPay = new AliPay();
//    aliPay.setSubject("aaa");
//    aliPay.setOut_trade_no("1231231231123");
//    aliPay.setTotal_amount("10.11");
//    String biz_content = JSON.toJSONString(aliPay);
//
//    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//    map.add("app_id", app_id);
//    map.add("method", method);
//    map.add("charset", charset);
//    map.add("sign_type", sign_type);
//    map.add("timestamp", timestamp);
//    map.add("version", version);
//    map.add("biz_content", biz_content);
//
//    String sign = AlipaySignature.rsaSign(AlipaySignature.getSignContent(map.toSingleValueMap()), private_key, charset, sign_type);
//    map.add("sign", sign);
//    RestTemplate restTemplate = new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//    System.out.println(response.getBody());
//  }

//  @Override
//  public String payNotify(Map<String, String[]> requestParams) {
//    Map<String, String> params = new HashMap<>();
//    for (Map.Entry<String, String[]> next : requestParams.entrySet()) {
//      String key = next.getKey();
//      String[] values = next.getValue();
//      String valueStr = "";
//      for (int i = 0; i < values.length; i++) {
//        valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
//      }
//      //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//      params.put(key, valueStr);
//    }
//    try {
//      boolean signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType()); //调用SDK验证签名
//      if (signVerified) {
//        //商户订单号
//        String out_trade_no = params.get("out_trade_no");
//        //交易状态
//        String trade_status = params.get("trade_status");
//        //交易金额
//        String amount = params.get("total_amount");
//        //交易时间
//        Date gmt_payment = new Date(params.get("gmt_payment"));
//        //商户app_id
//        String app_id = params.get("app_id");
//        if (("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) && alipayConfig.getAppId().equals(app_id)) {
//          OrderVO orderVO = orderService.selectByNo(out_trade_no);
//          if (orderVO != null && new BigDecimal(amount).compareTo(orderVO.getTotalPrice()) == 0) {
//            Order order = new Order();
//            order.setOrderNo(out_trade_no);
//            order.setPayTime(gmt_payment);
//            order.setStatus(OrderUtil.STATUS4);
//            order.setPayMethod("支付宝");
//            orderService.modifyOrder(order);
//          }
//        } else {
//          System.out.println("没有处理支付宝回调业务，支付宝交易状态：{},params:{}" + trade_status + params);
//        }
//      } else {
//        return "failure";
//      }
//    } catch (AlipayApiException e) {
//      return "failure";
//    }
//    return "success";
//  }

//  @Override
//  public void checkPayStatus(String orderNo) throws AlipayApiException {
//    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
//    Map<String, String> bizContent = new HashMap<>();
//    bizContent.put("out_trade_no", orderNo);
//    request.setBizContent(JSON.toJSONString(bizContent));
//    AlipayTradeQueryResponse response = alipayClient.execute(request);
//    if (response.isSuccess()) {
//      System.out.println("调用成功");
//      String out_trade_no = response.getOutTradeNo();
//      OrderVO orderVO = orderService.selectByNo(out_trade_no);
//      //支付成功
//      if ("TRADE_SUCCESS".equals(response.getTradeStatus())) {
//        String totalAmount = response.getTotalAmount();
//        Date sendPayDate = response.getSendPayDate();
//        if (orderVO != null && totalAmount.equals(orderVO.getTotalPrice().toString())) {
//          Order order = new Order();
//          order.setOrderNo(out_trade_no);
//          order.setPayTime(sendPayDate);
//          order.setStatus(OrderUtil.STATUS4);
//          order.setPayMethod("支付宝");
//          orderService.modifyOrder(order);
//        }
//      } else if ("TRADE_CLOSED".equals(response.getTradeStatus())) {
//        Order order = new Order();
//        order.setOrderNo(out_trade_no);
//        order.setStatus(OrderUtil.STATUS2);
//        orderService.checkDelTimeOut(out_trade_no, OrderUtil.STATUS2, true);
//      }
//      //其他
//    } else {
//      System.out.println("调用失败,订单暂时不存在,检查订单是否超时");
//      String out_trade_no = response.getOutTradeNo();
//      orderService.checkDelTimeOut(out_trade_no, OrderUtil.STATUS2, false);
//    }
//  }


}
