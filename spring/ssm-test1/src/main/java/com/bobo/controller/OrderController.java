package com.bobo.controller;


import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.dto.OrderDTO;
import com.bobo.pojo.query.OrderQuery;
import com.bobo.pojo.vo.OrderVO;
import com.bobo.service.OrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-19
 */
@Api(value = " API", tags = "")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

  @Autowired
  private OrderService orderService;

  @ApiOperation("下订单 ")
  @PostMapping
  @EnableLog("新增 ")
  public Result<?> addOrder(@Valid @RequestBody OrderDTO dto) {
    dto.setUserId(getUser().getUserId());
    return Result.success(orderService.placeOrder(dto));
  }

  @ApiOperation("选择支付 ")
  @PostMapping("/toPay")
  @EnableLog("选择支付 ")
  public Result<?> toPay(@Valid @RequestBody OrderDTO dto) {
    dto.setUserId(getUser().getUserId());
    return Result.success(orderService.toPay(dto));
  }


  @ApiOperation("删除 ")
  @DeleteMapping("/{id}")
  @EnableLog("删除 ")
  public Result<?> deleteOrder(@PathVariable Integer id) {
    orderService.deleteById(id);
    return Result.success();
  }


  @ApiOperation("根据id获取  详情")
  @GetMapping("/{id}")
  @EnableLog("根据id获取  详情")
  public Result<?> queryOrderById(@PathVariable Integer id) {
    OrderVO vo = orderService.selectById(id);
    return Result.success(vo);
  }

  @ApiOperation("根据No获取  详情")
  @GetMapping("/getByNo/{orderNo}")
  @EnableLog("根据id获取  详情")
  public Result<?> queryOrderByNo(@PathVariable String orderNo) {
    OrderVO vo = orderService.selectByNo(orderNo);
    return Result.success(vo);
  }


  @ApiOperation("分页查询 ")
  @PostMapping("/page")
  @EnableLog("分页查询  ")
  public Result<?> pagingList(@Valid @RequestBody OrderQuery query) {
    PageInfo<OrderVO> page = orderService.pageList(query);
    return Result.success(page);
  }
}
