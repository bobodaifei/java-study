package com.bobo.controller;


import com.alipay.api.AlipayApiException;
import com.bobo.pojo.dto.AliPayDTO;
import com.bobo.service.AliPayService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
