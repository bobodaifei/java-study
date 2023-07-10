# 微信网关

## 请求支付表单字符串

**网关接口**：`http://m.jnbat.com:8080/PayGateway/wxpay/pay`

**请求方式**：post

**ContentType**：application/json

**传递参数**：

|   参数名称   | 参数类型 | 是否必选 | 最大长度 | 参数说明                                                                                       | 备注   |
| :----------: | -------- | -------- | -------- | ---------------------------------------------------------------------------------------------- | ------ |
|     body     | String   | 必选     | 128      | 商品简单描述                                                                                   | 全英文 |
| out_trade_no | String   | 必选     | 32       | 商户订单号，由商家自定义，6-32个字符以内<br />只能是数字、大小写字母_-，且在同一个商户号下唯一 |        |
|  total_fee  | Integer  | 必选     | 11       | 订单总金额，单位为分                                                                           |        |
| redirect_url | String   | 必选     | 256      | 支付成功后前端将要跳转的指定页面，HTTP/HTTPS开头字符串                                         |        |
|  notify_url  | String   | 必选     | 256      | 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。                     |        |

**接收类型**：见实体1

返回结果：

```json
{
	"code":200,
	"msg":"success",
	"data":"https://wx.tenpay.com/cgi-bin/mmpayweb-bin/checkmweb?prepay_id=wx2016121516420242444321ca0631331346&package=1405458241&redirect_url=http://m.jnbat.com/home"
}
```

拿到data中的支付链接后，前端使用window.open或href重定向即可，前端的域名必须是公司域名，否则会出现商家未配置的问题，因为微信支付只能在指定域名下才能使用

## 异步回调支付结果

网关会根据notify_url来返回支付状态信息，请在本地测试一下

**接口方式**：post

**接收类型**：实体1，此时T的类型为实体2

根据接收数据修改数据库即可

实体1：

```java
package com.bobo.base;

public class Result<T> {
    //判断是否成功
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

```

实体2：

```java
@Data
public class WXPayVO {
  private String out_trade_no;//订单号

  private String result_code;//支付是否成功

  private Integer settlement_total_fee;//实付金额

  private String time_end;//支付时间

}
```
