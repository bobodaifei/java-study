package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.WxpayQuery;
import com.example.pojo.dto.WxpayDTO;
import com.example.pojo.vo.WxpayVO;
import com.example.base.Result;
import com.example.service.WxpayService;

import java.util.List;
/**
* <p>
    *  前端控制器
    * </p>
*
* @author bobodaifei
* @since 2023-07-03
*/
    @Api(value = " API", tags = "")
    @RestController
@RequestMapping("/wxpay")
    public class WxpayController {
@Autowired
private WxpayService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addWxpay(@Valid @RequestBody WxpayDTO dto) {
    service.addWxpay(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyWxpay(@PathVariable Long id, @Valid @RequestBody WxpayDTO dto) {
        service.modifyWxpay(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteWxpay(@PathVariable Long id) {
            service.deleteWxpay(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryWxpayById(@PathVariable Long id) {
                WxpayVO vo = service.queryWxpayById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody WxpayQuery query) {
                    Page<WxpayVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
