package com.bobo.utils;

import java.io.FileWriter;
import java.io.IOException;

import lombok.Data;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
@Data
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2021000122664646";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJ7iUYIKSS3gt77+gwoU//fY0t+sI3YIphtH82fba6r2PWLOQ1M3EiiZCGJHJ9LoL2MPWXcX+GdlJeZuxxWS08IC8cPMpCMQs3IyM22BuJ9Zj8wpzyWNAL+lWdWszHsTusCkvXjf58DkxBGPAiZxr+ZOOrh66doFInoWV34WZjxZQ3Q/kRYGvjnbAn1+yWoHthPOV8Jai7KxY0o39MNya6aCvPSidA6YQtVo8qXecM0idjaPp16qit9g5vqMIJ1XRxVL+8hNqZ7NQO31K6Fo3s2pwTSKl0pU4eZZYDTDDAiBdBSh0VXrbZY+NUT3RY6iJ4JgopNen4fvlmU41EFzlPAgMBAAECggEAJvIoZTSqY5Hw+jDWhgy2cvS3jXkVp99Jk6cMmI1MsE/fx9TrJx58Xn0rYsUd0rz0W6GdZwK0OHpmbaNwNd0xSZ3oz3dWwPPqL8prIgxJUl4XsZ8eA8wSCp/H1uORmP0xVTWfDqFYbxz2aRcqjvxAAf7ozkFEi9t99xS6euwZJihw6iWWrusV6xy+e1fKo5V9pQvXRKDnDSHUoHB8u7WlrPRQiYDD/JBhBsauWdMinbvx7nZ33H4939lIVeEHI9avyvWUo/Xx2LT1fSr4P8h09Y3IHM+WnetY1bljZKForiCfKUmHX0CBQCxbCsgWJN5T8/iIaoR7p70+AHQTdP2T4QKBgQD1rsbU/Am9CfcIjeIdvdQnIQdwTxHAd/roW3ioHWzb7vrpMM6xuvgYKp1Wlnbn8SsKLu2qZ6LpQZWSxZb/EAOwwKks8X6u6NHGVXpj2GbWAE+3OW1GgSZ92Qfzz7O0rBKU0ygNetkIZt6B1Owo9WEph8Pn2j7mjmR/yMOs6m0ckQKBgQCPuPhrQLYyJZrYkqp0M15DCKT0ygXz/kg9a/klZeZl0XHcNS/OtQPzkZ62jtGIonVUbNKLyr2jCNafrBkUYKaQzKM5IHSVUep6uKebk3jz7B4GMmKxImvPFIrJVoQBhCa/ezlx/qe01E5N6BispKVHikt36cUVbGMwz5I6uj1n3wKBgQCVjKdBvI3dAXTbQyqx+fxrOSLDDY57v0z/JbcDdq3uDsGzMt+OtoYQh9gwffSxaU9OqpRHV0CGhkKbDiXE6yWj8RLJ5dK8e8I93YhbwL2QZK4hlRZchX89ud+qXPNB9kB1UTefRPlZsOye0VJT+FIFqrvopSQGygSI+FeMKULz0QKBgFWWnbmhS98JLfGTRhb1EElzOGjedjUbVQHe7vwqds5jsB6nPdUnTzcZnR6yZm2r8ZtZQvkkv1H7zXRBFJ5iAE9yzjES9tqFcdGrbYTkib8Y+bhPAR7jlLg5T30fHHWasUiOpWMRd+XkGM2kTi1hz1uPK+o5OLjNVzSWKEr45eCZAoGAZRI7GnbVhP61zqlOOIBsYCT8F4UAMWGP5ZTpttxU1CF5veAz6EOYMz3nIh5JDYBkFEagmAiuii5Y6JrYTjJIyTHjv0TLqhUZcxjpXhDLkptaGVofMHD7odG7bGfD+0JMDZNQALyA67BZ941tp0nNbjCguoqH5pA3n7RqUOW5/6Y=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqZmet9S2/R8o1PlvINlRFdMaq5V76c6DLIYb/tcgYgeVpdcLa4pbMDPXpr08JRvLAjCIcLjX4lUnDoKCSkGHhm+PKBsY5bktDQf75h/2j6/JjyHnfcrbT7Ora32RO1mdphN0OjdB7mf/p+8r3Wq22nIMSGHvUWurn8AlUaSl68l3OKzEGvjOKtzDdVrtIfDwKBgg6G25skSshgn2pqaaFTCFxoG4coW1dVDlg3cxLL2B3XGxisYKbIId93F52Cfz/qESDVroRNs3Mo9A9BLJ0MauGUNYgVLJrv25biJOkPoxhCG317w8PpCCs5bl69LwNk/Y3Y6SeW1bq5q7EG02zwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "E:/code/";

	public static String format = "json";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

