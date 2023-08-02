package com.bobo.purview.component.convert;

import com.bobo.purview.entity.Menu;
import com.bobo.purview.pojo.dto.MenuDTO;
import com.bobo.purview.pojo.vo.MenuVO;
import com.bobo.purview.utils.MapStructUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-27T11:14:12+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
public class MenuConvertImpl implements MenuConvert {

    @Override
    public Menu toEntity(MenuDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setMenuId( MapStructUtil.absolute( dto.getId() ) );
        menu.setTitle( dto.getTitle() );
        menu.setRouteName( dto.getRouteName() );
        menu.setRoutePath( dto.getRoutePath() );
        menu.setPid( dto.getPid() );
        menu.setSort( dto.getSort() );
        menu.setIcon( dto.getIcon() );
        menu.setLevel( dto.getLevel() );

        return menu;
    }

    @Override
    public List<Menu> toEntities(List<MenuDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( dtos.size() );
        for ( MenuDTO menuDTO : dtos ) {
            list.add( toEntity( menuDTO ) );
        }

        return list;
    }

    @Override
    public MenuVO toVO(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuVO menuVO = new MenuVO();

        menuVO.setId( MapStructUtil.absolute( entity.getMenuId() ) );
        menuVO.setMenuId( entity.getMenuId() );
        menuVO.setTitle( entity.getTitle() );
        menuVO.setRouteName( entity.getRouteName() );
        menuVO.setRoutePath( entity.getRoutePath() );
        menuVO.setPid( entity.getPid() );
        menuVO.setSort( entity.getSort() );
        menuVO.setIcon( entity.getIcon() );
        menuVO.setLevel( entity.getLevel() );
        List list = entity.getChildren();
        if ( list != null ) {
            menuVO.setChildren( new ArrayList( list ) );
        }
        menuVO.setHasMenuChildren( entity.isHasMenuChildren() );

        return menuVO;
    }

    @Override
    public List<MenuVO> toVOS(List<Menu> entities) {
        if ( entities == null ) {
            return null;
        }

        List<MenuVO> list = new ArrayList<MenuVO>( entities.size() );
        for ( Menu menu : entities ) {
            list.add( toVO( menu ) );
        }

        return list;
    }
}
