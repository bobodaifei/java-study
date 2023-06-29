package com.example.controller;

import org.springframework.web.bind.annotation.*;
    import org.springframework.web.bind.annotation.RestController;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import javax.validation.Valid;
import com.example.aop.EnableLog;
import com.example.pojo.query.UserQuery;
import com.example.pojo.dto.UserDTO;
import com.example.pojo.vo.UserVO;
import com.example.base.Result;
import com.example.service.UserService;

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
@RequestMapping("/user")
    public class UserController {
@Autowired
private UserService service;

    @ApiOperation("新增 ")
@PostMapping
@EnableLog("新增 ")
public Result<?> addUser(@Valid @RequestBody UserDTO dto) {
    service.addUser(dto);
    return Result.success();
    }

        @ApiOperation("修改 ")
    @PutMapping("/{id}")
    @EnableLog("修改 ")
    public Result<?> modifyUser(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        service.modifyUser(id, dto);
        return Result.success();
        }

            @ApiOperation("删除 ")
        @DeleteMapping("/{id}")
        @EnableLog("删除 ")
        public Result<?> deleteUser(@PathVariable Long id) {
            service.deleteUser(id);
            return Result.success();
            }


                @ApiOperation("根据id获取  详情")
            @GetMapping("/{id}")
            @EnableLog("根据id获取  详情")
            public Result<?> queryUserById(@PathVariable Long id) {
                UserVO vo = service.queryUserById(id);
                return Result.success(vo);
                }


                    @ApiOperation("分页查询 ")
                @PostMapping("/page")
                @EnableLog("分页查询  ")
                public Result<?> pagingList(@Valid @RequestBody UserQuery query) {
                    Page<UserVO> page = service.pageList(query);
                        return Result.success(page);
                        }
                        }
