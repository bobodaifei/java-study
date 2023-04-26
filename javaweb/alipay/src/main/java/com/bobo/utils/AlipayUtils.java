package com.bobo.utils;

import java.util.ResourceBundle;

import com.alipay.easysdk.kernel.Config;

//Config 可以在spring注入bean
public class AlipayUtils {
  
  private static final String APPID = getValue("appId");
  private static final String APPPRIVATEKEY = getValue("appPrivateKey");
  private static final String ALIPAYPUBLICKEY = getValue("alipayPublicKey");
  private static final String NOTIFYURL = getValue("notifyUrl");
  private static final String ENCRYPTKEY = getValue("encryptKey");



  private static String getValue(String key) {
    // 资源包绑定
    // ResourceBundle与Properties的区别在于
    // ResourceBundle通常是用于国际化的属性配置文件读取，Properties则是一般的属性配置文件读取。
    ResourceBundle bundle = ResourceBundle.getBundle("alipay");
    return bundle.getString(key);
  }
  

  public static Config getOptions() {
    Config config = new Config();
    config.protocol = "https";
    //旧版网关
    // config.gatewayHost = "openapi.alipaydev.com";
    //新版网关
    config.gatewayHost = "openapi-sandbox.dl.alipaydev.com";
    config.signType = "RSA2";

    config.appId = APPID;
    // 为避免私钥随源码泄露，推荐从文件中读取私钥字符串而不是写入源码中
    config.merchantPrivateKey = APPPRIVATEKEY;
    // 注：证书文件路径支持设置为文件系统中的路径或CLASS_PATH中的路径，优先从文件系统中加载，加载失败后会继续尝试从CLASS_PATH中加载
    // config.merchantCertPath = "<-- 请填写您的应用公钥证书文件路径，例如：/foo/appCertPublicKey_2019051064521003.crt -->";
    // config.alipayCertPath = "<-- 请填写您的支付宝公钥证书文件路径，例如：/foo/alipayCertPublicKey_RSA2.crt -->";
    // config.alipayRootCertPath = "<-- 请填写您的支付宝根证书文件路径，例如：/foo/alipayRootCert.crt -->";
    
    // 注：如果采用非证书模式，则无需赋值上面的三个证书路径，改为赋值如下的支付宝公钥字符串即可
    config.alipayPublicKey = ALIPAYPUBLICKEY;
    // 可设置异步通知接收服务地址（可选）
    // config.notifyUrl = NOTIFYURL;


    // 可设置AES密钥，调用AES加解密相关接口时需要（可选）
    config.encryptKey = ENCRYPTKEY;
    return config;
  }

  public static void main(String[] args) {
    System.out.println(getOptions());
  }
}
