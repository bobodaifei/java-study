package com.bobo.gateway.controller;

import com.bobo.gateway.base.Result;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.pojo.dto.WXpayDTO;
import com.bobo.gateway.service.WXpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/wxpay")
public class WXpayController {

  @Autowired
  WXpayService wxpayService;

  @Autowired
  HttpServletRequest request;

  @PostMapping("/pay")
  public Result<?> pay(@RequestBody WXpayDTO wxpayDTO) throws CustomException {
    wxpayDTO.setSpbill_create_ip(request.getRemoteAddr());
    System.out.println(wxpayDTO.toString());
    return Result.success(wxpayService.pay(wxpayDTO));
  }

  @PostMapping("/notify")
  public String payNotify(@RequestBody String xmlResp) throws CustomException {
    return wxpayService.payNotify(xmlResp);
  }

}