package com.example.component.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.entity.Student;
import com.example.pojo.dto.StudentDTO;
import com.example.pojo.vo.StudentVO;

import java.util.List;

/**
 * <p>
 * 转换类
 * </p>
 *
 * @author bobodaifei
 * @since 2023-06-07
 */

@Mapper
public interface StudentConvert {

  StudentConvert INSTANCE = Mappers.getMapper(StudentConvert.class);

  Student toEntity(StudentDTO dto);

  List<Student> toEntities(List<StudentDTO> dtos);

  StudentVO toVO(Student entity);

  List<StudentVO> toVOS(List<Student> entities);

}

