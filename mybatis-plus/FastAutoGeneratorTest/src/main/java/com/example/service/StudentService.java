package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Student;
import com.example.pojo.query.StudentQuery;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.vo.StudentVO;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */
public interface StudentService extends IService<Student> {

  /**
   * 新增
   *
   * @param dto 参数
   */
  void addStudent(StudentDTO dto);

  /**
   * 修改
   *
   * @param dto 参数
   */
  void modifyStudent(String id, StudentDTO dto);

  /**
   * 删除
   *
   * @param id 主键
   */
  void deleteStudent(String id);

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  StudentVO queryStudentById(String id);


  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  Page<StudentVO> pageList(StudentQuery query);
}
