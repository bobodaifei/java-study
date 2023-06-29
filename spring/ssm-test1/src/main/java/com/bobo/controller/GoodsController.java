package com.bobo.controller;


import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.query.GoodsQuery;
import com.bobo.pojo.vo.GoodsVO;
import com.bobo.service.GoodsService;
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
@RequestMapping("/goods")
public class GoodsController {
  @Autowired
  private GoodsService goodsService;

  @ApiOperation("根据id获取  详情")
  @GetMapping("/{id}")
  @EnableLog("根据id获取  详情")
  public Result<?> queryGoodsById(@PathVariable Integer id) {
    GoodsVO vo = goodsService.selectById(id);
    return Result.success(vo);
  }


  @ApiOperation("分页查询 ")
  @PostMapping("/page")
  @EnableLog("分页查询  ")
  public Result<?> pagingList(@Valid @RequestBody GoodsQuery query) {
    PageInfo<GoodsVO> page = goodsService.pageList(query);
    return Result.success(page);
  }
}
