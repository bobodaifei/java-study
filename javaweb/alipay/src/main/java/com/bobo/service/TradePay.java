package com.bobo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.bobo.utils.AlipayConfig;

//测试当面付2.0支付（使用未集成交易保障接口的当面付2.0服务） 我扫你
public class TradePay extends AliPayBasic {
  public static void main(String[] args) throws AlipayApiException {
    // 网页支付
    // trade_page_pay();
    // 确认是否完成
    // trade_query();
    // 退款
    // trade_refund();
    // 商家主动关闭
    // trade_close();
    // 对账
    bill_downloadurl_query();

  }

  public static void trade_page_pay() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(
        AlipayConfig.gatewayUrl,
        AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
    request.setNotifyUrl("");
    request.setReturnUrl("www.baidu.com");
    JSONObject bizContent = new JSONObject();
    bizContent.put("out_trade_no", "20210817010101011");
    bizContent.put("total_amount", 0.01);
    bizContent.put("subject", "测试商品");
    bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
    // bizContent.put("time_expire", "2022-08-01 22:00:00");

    //// 商品明细信息，按需传入
    // JSONArray goodsDetail = new JSONArray();
    // JSONObject goods1 = new JSONObject();
    // goods1.put("goods_id", "goodsNo1");
    // goods1.put("goods_name", "子商品1");
    // goods1.put("quantity", 1);
    // goods1.put("price", 0.01);
    // goodsDetail.add(goods1);
    // bizContent.put("goods_detail", goodsDetail);

    //// 扩展信息，按需传入
    // JSONObject extendParams = new JSONObject();
    // extendParams.put("sys_service_provider_id", "2088511833207846");
    // bizContent.put("extend_params", extendParams);

    request.setBizContent(bizContent.toString());
    AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
    if (response.isSuccess()) {
      System.out.println(response.getBody());
      System.out.println(response.getTradeNo());
      System.out.println("调用成功");
    } else {
      System.out.println("调用失败");
    }
  }

  public static void trade_query() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(
        AlipayConfig.gatewayUrl,
        AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
    JSONObject bizContent = new JSONObject();
    bizContent.put("out_trade_no", "20210817010101007");
    // bizContent.put("trade_no", "2014112611001004680073956707");
    request.setBizContent(bizContent.toString());
    AlipayTradeQueryResponse response = alipayClient.execute(request);
    if (response.isSuccess()) {

      System.out.println("调用成功");
      System.out.println(response.getBody());
    } else {
      System.out.println("调用失败");
    }
  }

  public static void trade_refund() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(
        AlipayConfig.gatewayUrl,
        AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
    JSONObject bizContent = new JSONObject();
    bizContent.put("trade_no", "2023042622001452640500053542");
    bizContent.put("refund_amount", 0.01);
    bizContent.put("out_request_no", "HZ01RF001");

    //// 返回参数选项，按需传入
    // JSONArray queryOptions = new JSONArray();
    // queryOptions.add("refund_detail_item_list");
    // bizContent.put("query_options", queryOptions);

    request.setBizContent(bizContent.toString());
    AlipayTradeRefundResponse response = alipayClient.execute(request);
    if (response.isSuccess()) {
      System.out.println("调用成功");
    } else {
      System.out.println("调用失败");
    }
  }

  public static void trade_close() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(
        AlipayConfig.gatewayUrl,
        AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
    JSONObject bizContent = new JSONObject();
    bizContent.put("out_trade_no", "20210817010101011");
    request.setBizContent(bizContent.toString());
    AlipayTradeCloseResponse response = alipayClient.execute(request);
    if (response.isSuccess()) {
      System.out.println("调用成功");
    } else {
      System.out.println("调用失败");
    }
  }

  public static void bill_downloadurl_query() throws AlipayApiException {
    AlipayClient alipayClient = new DefaultAlipayClient(
        AlipayConfig.gatewayUrl,
        AlipayConfig.app_id,
        AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
        AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
    AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
    request.setBizContent("{" +
        "  \"bill_type\":\"trade\"," +
        "  \"bill_date\":\"2020-04-05\"" +
        // " \"smid\":\"2088123412341234\"" +
        "}");
    AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
    if (response.isSuccess()) {
      System.out.println("调用成功");
      // System.out.println(response.getBody());
      down(response.getBillDownloadUrl());
    } else {
      System.out.println("调用失败");
    }
  }

  public static void down(String urlStr) {
    // 指定希望保存的文件路径
    String filePath = "E:/code/fund_bill_20210205.csv";
    URL url = null;
    HttpURLConnection httpUrlConnection = null;
    InputStream fis = null;
    FileOutputStream fos = null;
    try {
      url = new URL(urlStr);
      httpUrlConnection = (HttpURLConnection) url.openConnection();
      httpUrlConnection.setConnectTimeout(5 * 1000);
      httpUrlConnection.setDoInput(true);
      httpUrlConnection.setDoOutput(true);
      httpUrlConnection.setUseCaches(false);
      httpUrlConnection.setRequestMethod("GET");
      httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
      httpUrlConnection.connect();
      fis = httpUrlConnection.getInputStream();
      byte[] temp = new byte[1024];
      int b;
      fos = new FileOutputStream(new File(filePath));
      while ((b = fis.read(temp)) != -1) {
        fos.write(temp, 0, b);
        fos.flush();
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fis != null)
          fis.close();
        if (fos != null)
          fos.close();
        if (httpUrlConnection != null)
          httpUrlConnection.disconnect();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
