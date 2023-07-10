package com.bobo.gateway.controller;


import com.bobo.gateway.base.Result;
import com.bobo.gateway.exception.CustomException;
import com.bobo.gateway.pojo.dto.AlipayDTO;
import com.bobo.gateway.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AlipayController {

  @Autowired
  AlipayService alipayService;

  @PostMapping("/pay")
  public Result<?> pay(@RequestBody AlipayDTO alipayDTO) throws CustomException {
    return Result.success(alipayService.pay(alipayDTO));
  }

  @PostMapping("/notify")
  public String payNotify(HttpServletRequest request) throws CustomException {
    Map<String, String[]> requestParams = request.getParameterMap();
    return alipayService.payNotify(requestParams);
  }

}
