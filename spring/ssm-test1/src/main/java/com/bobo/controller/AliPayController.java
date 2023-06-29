package com.bobo.controller;


import com.alipay.api.AlipayApiException;
import com.bobo.pojo.dto.AliPayDTO;
import com.bobo.service.AliPayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(value = " API", tags = "")
@RestController
@RequestMapping("/alipay")
@CrossOrigin
public class AliPayController extends BaseController {

  @Autowired
  AliPayService aliPayService;

  @GetMapping("/pay")
  public String pay(AliPayDTO dto) throws AlipayApiException {
    return aliPayService.pay(dto);
  }

  @PostMapping("/notify")
  public String payNotify(HttpServletRequest request,@RequestBody String body) {
    Map<String, String[]> requestParams = request.getParameterMap();
    return aliPayService.payNotify(requestParams);
  }

}
