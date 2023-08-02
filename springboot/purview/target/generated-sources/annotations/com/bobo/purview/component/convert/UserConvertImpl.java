package com.bobo.purview.component.convert;

import com.bobo.purview.entity.User;
import com.bobo.purview.pojo.dto.UserDTO;
import com.bobo.purview.pojo.vo.UserVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-27T11:14:12+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
public class UserConvertImpl implements UserConvert {

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( dto.getUserName() );
        user.setPassword( dto.getPassword() );
        if ( dto.getRoleId() != null ) {
            user.setRoleId( Integer.parseInt( dto.getRoleId() ) );
        }

        return user;
    }

    @Override
    public List<User> toEntities(List<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( dtos.size() );
        for ( UserDTO userDTO : dtos ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }

    @Override
    public UserVO toVO(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserVO userVO = new UserVO();

        userVO.setUserId( entity.getUserId() );
        userVO.setUserName( entity.getUserName() );
        userVO.setPassword( entity.getPassword() );
        if ( entity.getRoleId() != null ) {
            userVO.setRoleId( String.valueOf( entity.getRoleId() ) );
        }

        return userVO;
    }

    @Override
    public List<UserVO> toVOS(List<User> entities) {
        if ( entities == null ) {
            return null;
        }

        List<UserVO> list = new ArrayList<UserVO>( entities.size() );
        for ( User user : entities ) {
            list.add( toVO( user ) );
        }

        return list;
    }
}
