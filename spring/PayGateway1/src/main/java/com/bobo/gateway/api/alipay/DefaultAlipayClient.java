package com.bobo.gateway.api.alipay;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.utils.alipay.AlipayConstants;
import com.bobo.gateway.utils.alipay.AlipayHashMap;
import com.bobo.gateway.utils.alipay.AlipaySignature;
import com.bobo.gateway.utils.alipay.RequestParametersHolder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class DefaultAlipayClient {

  private String serverUrl;

  private String appId;

  private String format;

  private String charset;

  private String signType;

  private String privateKey;

  private String alipayPublicKey;

  private String alipaySdk = "alipay-sdk-java-dynamicVersionNo";

  private AlipaySigner signer;

  //任务1：生成form表单
  public DefaultAlipayClient(AlipayConfig config) {
    this.serverUrl = config.getServerUrl();
    this.appId = config.getAppId();
    this.format = config.getFormat();
    this.charset = config.getCharset();
    this.signType = config.getSignType();
    this.privateKey = config.getPrivateKey();
    this.alipayPublicKey = config.getAlipayPublicKey();
    this.signer = new AlipaySigner(config.getPrivateKey());
  }

  public AlipayResponse pageExecute(AlipayRequest request) throws CustomException {
    RequestParametersHolder requestHolder = getRequestHolderWithSign(request);
    AlipayResponse response = new AlipayResponse();
    String baseUrl = getRequestUrl(requestHolder);
    response.setBody(buildForm(baseUrl, requestHolder.getApplicationParams()));
    if (StrUtil.isEmpty(response.getBody())){
      response.setCode("-1");
      response.setMsg("表单为空");
    }else {
      response.setCode("0");
    }
    return response;
  }

  private RequestParametersHolder getRequestHolderWithSign(AlipayRequest request) throws CustomException {

    RequestParametersHolder requestHolder = new RequestParametersHolder();

    AlipayHashMap appParams = new AlipayHashMap(request.getTextParams());
    requestHolder.setApplicationParams(appParams);

    AlipayHashMap protocalOptParams = new AlipayHashMap();
    protocalOptParams.put(AlipayConstants.FORMAT, this.format);//format
    protocalOptParams.put(AlipayConstants.ALIPAY_SDK, this.alipaySdk);//alipaySdk
    requestHolder.setProtocalOptParams(protocalOptParams);

    AlipayHashMap protocalMustParams = new AlipayHashMap();
    protocalMustParams.put(AlipayConstants.METHOD, request.getMethod());//method
    protocalMustParams.put(AlipayConstants.VERSION, request.getVersion());//version
    protocalMustParams.put(AlipayConstants.APP_ID, this.appId);//appid
    protocalMustParams.put(AlipayConstants.SIGN_TYPE, this.signType);//sign_type
    protocalMustParams.put(AlipayConstants.NOTIFY_URL, request.getNotifyUrl());//notify_url
    protocalMustParams.put(AlipayConstants.RETURN_URL, request.getReturnUrl());//return_url
    protocalMustParams.put(AlipayConstants.CHARSET, this.charset);//charset
    protocalMustParams.put(AlipayConstants.TIMESTAMP, DateUtil.now());//timestamp
    requestHolder.setProtocalMustParams(protocalMustParams);

    if (StrUtil.isNotEmpty(this.signType)) {
      String content = AlipaySignature.getSignatureContent(requestHolder);
      protocalMustParams.put(AlipayConstants.SIGN, getSigner().sign(content, this.charset));//sign
    } else {
      protocalMustParams.put(AlipayConstants.SIGN, "");
    }
    return requestHolder;
  }

  private String getRequestUrl(RequestParametersHolder requestHolder) throws CustomException {
    StringBuilder urlSb = new StringBuilder(this.serverUrl);

    try {
      String sysMustQuery = buildQuery(requestHolder.getProtocalMustParams(), charset);
      String sysOptQuery = buildQuery(requestHolder.getProtocalOptParams(), charset);

      urlSb.append("?");
      urlSb.append(sysMustQuery);
      if (sysOptQuery != null && sysOptQuery.length() > 0) {
        urlSb.append("&");
        urlSb.append(sysOptQuery);
      }
    } catch (IOException e) {
      throw new CustomException("-1", "字符编码出现异常");
    }
    return urlSb.toString();
  }

  public static String buildQuery(Map<String, String> params, String charset) throws IOException {
    if (params == null || params.isEmpty()) {
      return null;
    }
    StringBuilder query = new StringBuilder();
    Set<Map.Entry<String, String>> entries = params.entrySet();
    boolean hasParam = false;
    for (Map.Entry<String, String> entry : entries) {
      String name = entry.getKey();
      String value = entry.getValue();
      // 忽略参数名或参数值为空的参数
      if (StrUtil.isAllNotEmpty(name, value)) {
        if (hasParam) {
          query.append("&");
        } else {
          hasParam = true;
        }
        query.append(name).append("=").append(URLEncoder.encode(value, charset));
      }
    }
    return query.toString();
  }

  public static String buildForm(String baseUrl, Map<String, String> parameters) {
    StringBuilder sb = new StringBuilder();
    sb.append("<form name=\"punchout_form\" method=\"post\" action=\"");
    sb.append(baseUrl);
    sb.append("\">\n");
    sb.append(buildHiddenFields(parameters));

    sb.append("<input type=\"submit\" value=\"立即支付\" style=\"display:none\" >\n");
    sb.append("</form>\n");
    sb.append("<script>document.forms[0].submit();</script>");
    return sb.toString();
  }

  private static String buildHiddenFields(Map<String, String> parameters) {
    if (parameters == null || parameters.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    Set<String> keys = parameters.keySet();
    for (String key : keys) {
      String value = parameters.get(key);
      // 除去参数中的空值
      if (key == null || value == null) {
        continue;
      }
      sb.append(buildHiddenField(key, value));
    }
    return sb.toString();
  }

  private static String buildHiddenField(String key, String value) {
    StringBuilder sb = new StringBuilder();
    sb.append("<input type=\"hidden\" name=\"");
    sb.append(key);

    sb.append("\" value=\"");
    //转义双引号
    String a = value.replace("\"", "&quot;");
    sb.append(a).append("\">\n");
    return sb.toString();
  }


  public AlipaySigner getSigner() {
    return signer;
  }
}


