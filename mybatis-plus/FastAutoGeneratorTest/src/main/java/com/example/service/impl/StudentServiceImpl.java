package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.component.convert.StudentConvert;
import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.query.StudentQuery;
import com.example.pojo.vo.StudentVO;
import com.example.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */
@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

  private final StudentConvert INSTANCE = StudentConvert.INSTANCE;

  /**
   * 新增
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addStudent(StudentDTO dto) {
    Student entity = INSTANCE.toEntity(dto);
    super.save(entity);
  }

  /**
   * 修改
   *
   * @param dto 参数
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void modifyStudent(String id, StudentDTO dto) {
    Student entity = INSTANCE.toEntity(dto);
    entity.setStudentNo(id);
    super.updateById(entity);
  }

  /**
   * 删除
   *
   * @param id 主键
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void deleteStudent(String id) {
    super.removeById(id);
  }

  /**
   * 根据id获取  详情
   *
   * @param id 主键
   */
  @Override
  public StudentVO queryStudentById(String id) {
    Student entity = super.getById(id);
    return INSTANCE.toVO(entity);
  }


  /**
   * 分页查询
   *
   * @param query 参数
   * @return
   */
  @Override
  public Page<StudentVO> pageList(StudentQuery query) {
    Page<Student> page = page(new Page<>(query.getCurrentPage(), query.getCurrentSize()),
            new LambdaQueryWrapper<Student>());
    List<StudentVO> resultList = INSTANCE.toVOS(page.getRecords());
    return new Page<StudentVO>().setTotal(page.getTotal()).setRecords(resultList);
  }
}
