package com.bobo.purview.component.convert;

import com.bobo.purview.entity.Role;
import com.bobo.purview.pojo.dto.RoleDTO;
import com.bobo.purview.pojo.vo.RoleVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-27T11:14:13+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
public class RoleConvertImpl implements RoleConvert {

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setName( dto.getName() );

        return role;
    }

    @Override
    public List<Role> toEntities(List<RoleDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtos.size() );
        for ( RoleDTO roleDTO : dtos ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public RoleVO toVO(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleVO roleVO = new RoleVO();

        roleVO.setRoleId( entity.getRoleId() );
        roleVO.setName( entity.getName() );

        return roleVO;
    }

    @Override
    public List<RoleVO> toVOS(List<Role> entities) {
        if ( entities == null ) {
            return null;
        }

        List<RoleVO> list = new ArrayList<RoleVO>( entities.size() );
        for ( Role role : entities ) {
            list.add( toVO( role ) );
        }

        return list;
    }
}
