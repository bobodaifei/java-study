package com.bobo.gateway.api.wxpay;

import cn.hutool.core.util.XmlUtil;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.utils.wxpay.RequestParametersHolder;
import com.bobo.gateway.utils.wxpay.WXpayConstants;
import com.bobo.gateway.utils.wxpay.WXpayHashMap;
import com.bobo.gateway.utils.wxpay.WXpaySignature;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class DefaultWXpayClient {

  private String serverUrl;//URL地址

  private String appid;//公众账号ID

  private String mchId;//商户号

  private String signType;//签名类型

  private String tradeType;//交易类型

  private String sceneInfo;//场景信息

  private String appKey;//密钥

  private WXpaySigner signer;


  public DefaultWXpayClient(WXpayConfig config) {
    this.serverUrl = config.getServerUrl();
    this.appid = config.getAppid();
    this.mchId = config.getMchId();
    this.signType = config.getSignType();
    this.tradeType = config.getTradeType();
    this.sceneInfo = config.getSceneInfo();
    this.appKey = config.getAppKey();
    this.signer = new WXpaySigner(this.appKey);
  }

  public WXpayResponse pageExecute(WXpayRequest request) throws CustomException {
    RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
    WXpayResponse response = new WXpayResponse();
    Map<String, String> sortedMap = WXpaySignature.getSortedMap(requestHolder);
    String xml = XmlUtil.mapToXmlStr(sortedMap, "xml", null, true, false);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_XML);
    HttpEntity<String> requestEntity = new HttpEntity<>(xml, headers);
    RestTemplate restTemplate = new RestTemplate();
    String res = null;
    try {
      res = restTemplate.postForObject(serverUrl, requestEntity, String.class);
    } catch (Exception e) {
      System.out.println("微信请求支付链接失败");
      throw new CustomException("-1", "微信请求支付链接失败");
    }
    response.setCode("0");
    response.setBody(res);
    System.out.println(res);
    return response;
  }

  private RequestParametersHolder getRequestHolderWithSign(WXpayRequest request) {
    RequestParametersHolder requestHolder = new RequestParametersHolder();

    WXpayHashMap appParams = new WXpayHashMap(request.getTextParams());
    requestHolder.setApplicationParams(appParams);

    WXpayHashMap protocalMustParams = new WXpayHashMap();
    protocalMustParams.put(WXpayConstants.APPID, this.appid);
    protocalMustParams.put(WXpayConstants.MCH_ID, this.mchId);
    protocalMustParams.put(WXpayConstants.SIGN_TYPE, this.signType);
    protocalMustParams.put(WXpayConstants.TRADE_TYPE, this.tradeType);
    protocalMustParams.put(WXpayConstants.SCENE_INFO, this.sceneInfo);
    requestHolder.setProtocalMustParams(protocalMustParams);

    String content = WXpaySignature.getSignatureContent(requestHolder, this.appKey);
    protocalMustParams.put(WXpayConstants.SIGN, getSigner().sign(content));//sign

    return requestHolder;
  }

  public WXpaySigner getSigner() {
    return signer;
  }
}
