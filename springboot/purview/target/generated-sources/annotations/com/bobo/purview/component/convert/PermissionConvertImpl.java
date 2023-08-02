package com.bobo.purview.component.convert;

import com.bobo.purview.entity.Permission;
import com.bobo.purview.pojo.dto.PermissionDTO;
import com.bobo.purview.pojo.vo.PermissionVO;
import com.bobo.purview.utils.MapStructUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-27T11:14:12+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
public class PermissionConvertImpl implements PermissionConvert {

    @Override
    public Permission toEntity(PermissionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setPermissionId( MapStructUtil.absolute( dto.getId() ) );
        permission.setTitle( dto.getTitle() );
        permission.setFunc( dto.getFunc() );
        permission.setUrl( dto.getUrl() );
        permission.setType( dto.getType() );
        permission.setMenuId( dto.getMenuId() );

        return permission;
    }

    @Override
    public List<Permission> toEntities(List<PermissionDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Permission> list = new ArrayList<Permission>( dtos.size() );
        for ( PermissionDTO permissionDTO : dtos ) {
            list.add( toEntity( permissionDTO ) );
        }

        return list;
    }

    @Override
    public PermissionVO toVO(Permission entity) {
        if ( entity == null ) {
            return null;
        }

        PermissionVO permissionVO = new PermissionVO();

        permissionVO.setId( MapStructUtil.negation( entity.getPermissionId() ) );
        permissionVO.setPermissionId( entity.getPermissionId() );
        permissionVO.setTitle( entity.getTitle() );
        permissionVO.setFunc( entity.getFunc() );
        permissionVO.setUrl( entity.getUrl() );
        permissionVO.setType( entity.getType() );
        permissionVO.setMenuId( entity.getMenuId() );

        return permissionVO;
    }

    @Override
    public List<PermissionVO> toVOS(List<Permission> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PermissionVO> list = new ArrayList<PermissionVO>( entities.size() );
        for ( Permission permission : entities ) {
            list.add( toVO( permission ) );
        }

        return list;
    }
}
