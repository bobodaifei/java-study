package com.bobo.service;

import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.model.builder.*;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FPrecreateResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.alipay.demo.trade.model.result.AlipayF2FRefundResult;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.utils.Utils;
import com.alipay.demo.trade.utils.ZxingUtils;
import org.apache.commons.lang.StringUtils;

//测试当面付2.0生成支付二维码
public class PrecreatePay extends AliPayBasic {

  public static void main(String[] args) {
    //你扫我
    test_trade_precreate();
    //确定订单是否成立
    // test_trade_query();
    //退款
    // test_trade_refund();

    //我扫你（需要用旧版）
    // test_trade_pay(tradeService);
  }

  // 测试当面付2.0生成支付二维码
  public static void test_trade_precreate() {
    // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
    // 需保证商户系统端不能重复，建议通过数据库sequence生成，
    String outTradeNo = "tradeprecreate" + System.currentTimeMillis()
        + (long) (Math.random() * 10000000L);

    // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店当面付扫码消费”
    String subject = "xxx品牌xxx门店当面付扫码消费";

    // (必填) 订单总金额，单位为元，不能超过1亿元
    // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
    String totalAmount = "0.01";

    // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
    String storeId = "2088721002552633";

    // 创建扫码支付请求builder，设置请求参数
    AlipayTradePrecreateRequestBuilder builder = new AlipayTradePrecreateRequestBuilder()
        .setSubject(subject).setTotalAmount(totalAmount).setOutTradeNo(outTradeNo).setStoreId(storeId);

    AlipayF2FPrecreateResult result = tradeService.tradePrecreate(builder);
    switch (result.getTradeStatus()) {
      case SUCCESS:
        log.info("支付宝预下单成功: )");

        AlipayTradePrecreateResponse response = result.getResponse();
        dumpResponse(response);

        // 需要修改为运行机器上的路径
        String filePath = String.format("E:/code/qr-%s.png",
            response.getOutTradeNo());
        log.info("filePath:" + filePath);
        ZxingUtils.getQRCodeImge(response.getQrCode(), 256, filePath);
        break;

      case FAILED:
        log.error("支付宝预下单失败!!!");
        break;

      case UNKNOWN:
        log.error("系统异常，预下单状态未知!!!");
        break;

      default:
        log.error("不支持的交易状态，交易返回异常!!!");
        break;
    }
  }

  // 测试当面付2.0支付
  public static void test_trade_pay(AlipayTradeService service) {
    // (必填) 商户网站订单系统中唯一订单号，64个字符以内，只能包含字母、数字、下划线，
    // 需保证商户系统端不能重复，建议通过数据库sequence生成，
    String outTradeNo = "tradepay" + System.currentTimeMillis()
        + (long) (Math.random() * 10000000L);

    // (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
    String subject = "xxx品牌xxx门店当面付消费";

    // (必填) 订单总金额，单位为元，不能超过1亿元
    // 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
    String totalAmount = "0.01";

    // (必填) 付款条码，用户支付宝钱包手机app点击“付款”产生的付款条码
    String authCode = "285147908095281126"; // 条码示例，286648048691290423
    // (可选，根据需要决定是否使用) 订单可打折金额，可以配合商家平台配置折扣活动，如果订单部分商品参与打折，可以将部分商品总价填写至此字段，默认全部商品可打折
    // 如果该值未传入,但传入了【订单总金额】,【不可打折金额】 则该值默认为【订单总金额】- 【不可打折金额】
    // String discountableAmount = "1.00"; //

    // (可选) 订单不可打折金额，可以配合商家平台配置折扣活动，如果酒水不参与打折，则将对应金额填写至此字段
    // 如果该值未传入,但传入了【订单总金额】,【打折金额】,则该值默认为【订单总金额】-【打折金额】
    String undiscountableAmount = "0.0";

    // 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
    // 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
    // String sellerId = "";

    // 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
    String body = "购买商品3件共20.00元";

    // 商户操作员编号，添加此参数可以为商户操作员做销售统计
    // String operatorId = "test_operator_id";

    // (必填) 商户门店编号，通过门店号和商家后台可以配置精准到门店的折扣信息，详询支付宝技术支持
    String storeId = "2088721002552633";

    // String appAuthToken = "应用授权令牌";// 根据真实值填写

    // 创建条码支付请求builder，设置请求参数
    AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
        // .setAppAuthToken(appAuthToken)
        .setOutTradeNo(outTradeNo).setSubject(subject).setAuthCode(authCode)
        .setTotalAmount(totalAmount).setStoreId(storeId)
        .setUndiscountableAmount(undiscountableAmount).setBody(body);

    // 调用tradePay方法获取当面付应答
    AlipayF2FPayResult result = service.tradePay(builder);
    switch (result.getTradeStatus()) {
      case SUCCESS:
        log.info("支付宝支付成功: )");
        break;

      case FAILED:
        log.error("支付宝支付失败!!!");
        break;

      case UNKNOWN:
        log.error("系统异常，订单状态未知!!!");
        break;

      default:
        log.error("不支持的交易状态，交易返回异常!!!");
        break;
    }
  }

  // 测试当面付2.0查询订单(非轮询)
  public static void test_trade_query() {
    // (必填) 商户订单号，通过此商户订单号查询当面付的交易状态
    String outTradeNo = "tradeprecreate16824289598033253094";

    // 创建查询请求builder，设置请求参数
    AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
        .setOutTradeNo(outTradeNo);

    AlipayF2FQueryResult result = tradeService.queryTradeResult(builder);
    switch (result.getTradeStatus()) {
      case SUCCESS:
        log.info("查询返回该订单支付成功: )");

        AlipayTradeQueryResponse response = result.getResponse();
        dumpResponse(response);

        if (Utils.isListNotEmpty(response.getFundBillList())) {
          for (TradeFundBill bill : response.getFundBillList()) {
            log.info(bill.getFundChannel() + ":" + bill.getAmount());
          }
        }
        break;

      case FAILED:
        log.error("查询返回该订单支付失败或被关闭!!!");
        break;

      case UNKNOWN:
        log.error("系统异常，订单支付状态未知!!!");
        break;

      default:
        log.error("不支持的交易状态，交易返回异常!!!");
        break;
    }
  }

  // 测试当面付2.0退款
  public static void test_trade_refund() {
    // (必填) 外部订单号，需要退款交易的商户外部订单号
    String outTradeNo = "tradeprecreate16824289598033253094";

    // (必填) 退款金额，该金额必须小于等于订单的支付金额，单位为元
    String refundAmount = "0.01";

    // (可选，需要支持重复退货时必填) 商户退款请求号，相同支付宝交易号下的不同退款请求号对应同一笔交易的不同退款申请，
    // 对于相同支付宝交易号下多笔相同商户退款请求号的退款交易，支付宝只会进行一次退款
    // String outRequestNo = "";

    // (必填) 退款原因，可以说明用户退款原因，方便为商家后台提供统计
    String refundReason = "正常退款，用户买多了";

    // (必填) 商户门店编号，退款情况下可以为商家后台提供退款权限判定和统计等作用，详询支付宝技术支持
    String storeId = "2088721002552633";

    // 创建退款请求builder，设置请求参数
    AlipayTradeRefundRequestBuilder builder = new AlipayTradeRefundRequestBuilder()
        .setOutTradeNo(outTradeNo).setRefundAmount(refundAmount).setRefundReason(refundReason)
        // .setOutRequestNo(outRequestNo)
        .setStoreId(storeId);

    AlipayF2FRefundResult result = tradeService.tradeRefund(builder);
    switch (result.getTradeStatus()) {
      case SUCCESS:
        log.info("支付宝退款成功: )");
        break;

      case FAILED:
        log.error("支付宝退款失败!!!");
        break;

      case UNKNOWN:
        log.error("系统异常，订单退款状态未知!!!");
        break;

      default:
        log.error("不支持的交易状态，交易返回异常!!!");
        break;
    }
  }

  // 简单打印应答
  private static void dumpResponse(AlipayResponse response) {
    if (response != null) {
      log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
      if (StringUtils.isNotEmpty(response.getSubCode())) {
        log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(),
            response.getSubMsg()));
      }
      log.info("body:" + response.getBody());
    }
  }

}
