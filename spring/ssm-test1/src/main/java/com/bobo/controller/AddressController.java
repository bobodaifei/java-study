package com.bobo.controller;


import com.bobo.aop.EnableLog;
import com.bobo.base.Result;
import com.bobo.pojo.dto.AddressDTO;
import com.bobo.pojo.vo.AddressVO;
import com.bobo.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@RequestMapping("/address")
public class AddressController extends BaseController{
  @Autowired
  private AddressService addressService;

  @ApiOperation("新增 ")
  @PostMapping
  @EnableLog("新增 ")
  public Result<?> addAddress(@Valid @RequestBody AddressDTO dto) {
    dto.setUserId(getUser().getUserId());
    addressService.addAddress(dto);
    return Result.success();
  }

  @ApiOperation("修改 ")
  @PutMapping("/{id}")
  @EnableLog("修改 ")
  public Result<?> modifyAddress(@PathVariable Integer id, @Valid @RequestBody AddressDTO dto) {
    addressService.modifyAddress(id, dto);
    return Result.success();
  }

  @ApiOperation("删除 ")
  @DeleteMapping("/{id}")
  @EnableLog("删除 ")
  public Result<?> deleteAddress(@PathVariable Integer id) {
    addressService.deleteById(id);
    return Result.success();
  }


  @ApiOperation("根据id获取  详情")
  @GetMapping("/{id}")
  @EnableLog("根据id获取  详情")
  public Result<?> queryAddressById(@PathVariable Integer id) {
    AddressVO vo = addressService.selectById(id);
    return Result.success(vo);
  }

  @ApiOperation("查询全部 ")
  @GetMapping("/list")
  @EnableLog("查询全部  ")
  public Result<?> selectList() {
    Integer userId = getUser().getUserId();
    List<AddressVO> list = addressService.selectList(userId);
    return Result.success(list);
  }

}
