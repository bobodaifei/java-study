package com.bobo.service;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.service.AlipayMonitorService;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayMonitorServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;
import com.alipay.demo.trade.service.impl.AlipayTradeWithHBServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AliPayBasic {
  protected static Log log = LogFactory.getLog(AliPayBasic.class);

  protected static AlipayTradeService tradeService;
  // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
  protected static AlipayTradeService tradeWithHBService;

  // 支付宝交易保障接口服务，供测试接口api使用，请先阅读readme.txt
  protected static AlipayMonitorService monitorService;

  static {
    /**
     * 一定要在创建AlipayTradeService之前调用Configs.init()设置默认参数
     * Configs会读取classpath下的zfbinfo.properties文件配置信息，如果找不到该文件则确认该文件是否在classpath目录
     */
    Configs.init("zfbinfo.properties");

    /**
     * 使用Configs提供的默认参数
     * AlipayTradeService可以使用单例或者为静态成员对象，不需要反复new
     */
    tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();

    // 支付宝当面付2.0服务（集成了交易保障接口逻辑）
    tradeWithHBService = new AlipayTradeWithHBServiceImpl.ClientBuilder().build();

    /** 如果需要在程序中覆盖Configs提供的默认参数, 可以使用ClientBuilder类的setXXX方法修改默认参数 否则使用代码中的默认设置 */
    monitorService = new AlipayMonitorServiceImpl.ClientBuilder()
        .setGatewayUrl("http://mcloudmonitor.com/gateway.do").setCharset("utf-8")
        .setFormat("json").build();
  }
}
