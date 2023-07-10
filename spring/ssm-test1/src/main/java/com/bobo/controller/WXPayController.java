package com.bobo.controller;

import com.alipay.api.AlipayApiException;
import com.bobo.base.Result;
import com.bobo.pojo.dto.WXPayDTO;
import com.bobo.pojo.vo.WXPayVO;
import com.bobo.service.WXService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = " API", tags = "")
@RestController
@RequestMapping("/wxpay")
@CrossOrigin
public class WXPayController {

  @Autowired
  WXService wxService;

  @Autowired
  private HttpServletRequest request;

  @GetMapping("/pay")
  public String pay(WXPayDTO dto) throws AlipayApiException {
    dto.setSpbill_create_ip(request.getRemoteAddr());
    return wxService.pay(dto);
  }

  @PostMapping("/notify")
  public Result<?> payNotify(@RequestBody Result<WXPayVO> result) throws AlipayApiException {
    System.out.println("微信支付回调"+result.getData().toString());
    wxService.payNotify(result.getData());
    return Result.success();
  }


}
