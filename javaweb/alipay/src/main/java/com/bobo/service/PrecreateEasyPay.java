package com.bobo.service;


import com.alipay.demo.trade.utils.ZxingUtils;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.factory.Factory.Payment;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.bobo.utils.AlipayUtils;


public class PrecreateEasyPay {
  public static void main(String[] args) throws Exception {
    // 1. 设置参数（全局只需设置一次）
    Factory.setOptions(AlipayUtils.getOptions());
    try {
      // 2. 发起API调用（以创建当面付收款二维码为例）
      AlipayTradePrecreateResponse response = Payment.FaceToFace().preCreate("Apple iPhone11 128G", "2333335555", "9.00");
      // 3. 处理响应或异常
      if (ResponseChecker.success(response)) {
        System.out.println("调用成功");
        String filePath = String.format("E:/code/qr-%s.png",response.getOutTradeNo());
        ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
      } else {
        System.err.println("调用失败，原因：" + response.msg + "，" + response.subMsg);
      }
    } catch (Exception e) {
      System.err.println("调用遭遇异常，原因：" + e.getMessage());
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  

  
}
