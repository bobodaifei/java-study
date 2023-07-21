package com.example.demo03.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo03.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
  @Select("SELECT username FROM role WHERE id IN (SELECT rid FROM role_user WHERE uid=(select id from user where username=#{principal}))")
  List<String> getUserRoleInfoMapper(@Param("principal") String principal);

  @Select({"<script>",
          "select info FROM permissions WHERE id IN ",
            "(SELECT pid FROM role_ps WHERE rid IN (",
            "SELECT id FROM role WHERE name IN ",
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>",
            "#{name}", "</foreach>",
            "))",
          "</script>"})
  List<String> getUserPermissionInfoMapper(@Param("roles") List<String> roles);
}
