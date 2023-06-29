package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.AddressQuery;
import com.example.pojo.dto.AddressDTO;
import com.example.pojo.vo.AddressVO;
import com.example.base.Result;
import com.example.service.AddressService;

import java.util.List;
/**
* <p>
    *  前端控制器
    * </p>
*
* @author bobodaifei
* @since 2023-06-19
*/
    @Api(value = " API", tags = "")
    @RestController
@RequestMapping("/address")
    public class AddressController {
@Autowired
private AddressService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addAddress(@Valid @RequestBody AddressDTO dto) {
    service.addAddress(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO dto) {
        service.modifyAddress(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteAddress(@PathVariable Long id) {
            service.deleteAddress(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryAddressById(@PathVariable Long id) {
                AddressVO vo = service.queryAddressById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody AddressQuery query) {
                    Page<AddressVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
