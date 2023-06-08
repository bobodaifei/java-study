package com.example.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.aop.EnableLog;
import com.example.base.Result;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.query.StudentQuery;
import com.example.pojo.vo.StudentVO;
import com.example.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */
@Api(value = " API", tags = "")
@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private StudentService service;

  @ApiOperation("新增 ")
  @PostMapping
  @EnableLog("新增 ")
  public Result<?> addStudent(@Valid @RequestBody StudentDTO dto) {
    service.addStudent(dto);
    return Result.success();
  }

  @ApiOperation("修改 ")
  @PutMapping("/{id}")
  @EnableLog("修改 ")
  public Result<?> modifyStudent(@PathVariable String id, @Valid @RequestBody StudentDTO dto) {
    service.modifyStudent(id, dto);
    return Result.success();
  }

  @ApiOperation("删除 ")
  @DeleteMapping("/{id}")
  @EnableLog("删除 ")
  public Result<?> deleteStudent(@PathVariable String id) {
    service.deleteStudent(id);
    return Result.success();
  }


  @ApiOperation("根据id获取  详情")
  @GetMapping("/{id}")
  @EnableLog("根据id获取  详情")
  public Result<?> queryStudentById(@PathVariable String id) {
    StudentVO vo = service.queryStudentById(id);
    return Result.success(vo);
  }


  @ApiOperation("分页查询 ")
  @PostMapping("/page")
  @EnableLog("分页查询  ")
  public Result<?> pagingList(@Valid @RequestBody StudentQuery query) {
    Page<StudentVO> page = service.pageList(query);
    return Result.success(page);
  }
}
