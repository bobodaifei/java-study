package com.bobo.purview.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bobo.purview.component.base.Result;
import com.bobo.purview.entity.Address;
import com.bobo.purview.entity.Emp;
import com.bobo.purview.service.AddressService;
import com.bobo.purview.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {

  @Autowired
  AddressService addressService;

  @GetMapping("/getAddress")
  public Result<?> getaddrTree(@RequestParam(required = false) String parentId) {
    List<Address> tree = addressService.tree(parentId);
    return Result.success(tree);
  }

  @PostMapping("/addAddress")
  public Result<?> addAddress(@RequestBody Address address) {
    boolean b = addressService.save(address);
    if (!b) {
      return Result.error("-1", "新增失败");
    }
    return Result.success();
  }

  @DeleteMapping("/deleteAddress/{id}")
  public Result<?> deleteAddress(@PathVariable String id) {
    LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Address::getParentId, id);
    long count = addressService.count(wrapper);
    if (count > 0) {
      return Result.error("-1", "存在子节点");
    }
    boolean b = addressService.removeById(id);
    if (!b) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }

  @Autowired
  EmpService empService;

  @GetMapping("/getEmpTree")
  public Result<?> getEmpTree(@RequestParam(required = false) Integer mgr) {
    List<Emp> tree = empService.tree(mgr);
    return Result.success(tree);
  }

  @PostMapping("/addEmp")
  public Result<?> addEmp(@RequestBody Emp emp) {
    boolean b = empService.save(emp);
    if (!b) {
      return Result.error("-1", "新增失败");
    }
    return Result.success();
  }

  @DeleteMapping("/deleteEmp/{empno}")
  public Result<?> deleteEmp(@PathVariable Integer empno) {
    LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
    wrapper.eq(Emp::getMgr, empno);
    long count = empService.count(wrapper);
    if (count > 0) {
      return Result.error("-1", "存在子节点");
    }
    boolean b = empService.removeById(empno);
    if (!b) {
      return Result.error("-1", "删除失败");
    }
    return Result.success();
  }


}
