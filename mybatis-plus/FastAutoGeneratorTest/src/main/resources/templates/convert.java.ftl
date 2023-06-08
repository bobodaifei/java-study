package ${package.Parent}.component.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ${package.Entity}.${entity};
import ${package.Parent}.pojo.dto.${entity}DTO;
import ${package.Parent}.pojo.vo.${entity}VO;

import java.util.List;

/**
* <p>
    * ${table.comment!} 转换类
    * </p>
*
* @author ${author}
* @since ${date}
*/

@Mapper
public interface ${entity}Convert {

${entity}Convert INSTANCE = Mappers.getMapper(${entity}Convert.class);

${entity} toEntity(${entity}DTO dto);

List<${entity}> toEntities(List<${entity}DTO> dtos);

    ${entity}VO toVO(${entity} entity);

    List<${entity}VO> toVOS(List<${entity}> entities);

        }
