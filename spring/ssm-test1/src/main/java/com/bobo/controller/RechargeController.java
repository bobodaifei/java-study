package com.bobo.controller;

import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.dto.RechargeAlipayNotifyDTO;
import com.bobo.pojo.dto.RechargeDTO;
import com.bobo.pojo.dto.RechargeWXpayNotifyDTO;
import com.bobo.pojo.query.RechargeQuery;
import com.bobo.pojo.vo.RechargeVO;
import com.bobo.service.RechargeService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recharge")
public class RechargeController extends BaseController {

  @Autowired
  RechargeService rechargeService;

  @PostMapping
  public Result<?> addOrder(@RequestBody RechargeDTO dto) throws InterruptedException {
    dto.setUserId(getUser().getUserId());
    return Result.success(rechargeService.placeOrder(dto));
  }

  @PostMapping("/toPay")
  public Result<?> toPay(@RequestBody RechargeDTO dto) {
    dto.setUserId(getUser().getUserId());
    return Result.success(rechargeService.toPay(dto));
  }

  @PostMapping("/alipayNotify")
  public void notify(@RequestBody Result<RechargeAlipayNotifyDTO> result) throws InterruptedException {
    System.out.println("系统：回调成功");
    rechargeService.payNotify1(result);
  }

  @PostMapping("/wxpayNotify")
  public Result<?> notify1(@RequestBody Result<RechargeWXpayNotifyDTO> result) throws InterruptedException {
    System.out.println("微信支付回调"+result.getData().toString());
    rechargeService.payNotify(result.getData());
    return Result.success();
  }

  @GetMapping("/getByNo/{rechargeNo}")
  public Result<?> queryRechargeByNo(@PathVariable String rechargeNo) {
    RechargeVO vo = rechargeService.selectByNo(rechargeNo);
    return Result.success(vo);
  }

  @ApiOperation("分页查询 ")
  @PostMapping("/page")
  @EnableLog("分页查询  ")
  public Result<?> pagingList(@RequestBody RechargeQuery query) {
    query.setUserId(getUser().getUserId());
    PageInfo<RechargeVO> page = rechargeService.pageList(query);
    return Result.success(page);
  }

}
