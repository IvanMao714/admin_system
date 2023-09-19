package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.base.RoleMenu;
import com.gsiv.springboot.utils.BaseMapperX;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
* @author 65151
* @description 针对表【T_ROLE_MENU】的数据库操作Mapper
* @createDate 2023-08-15 21:37:36
* @Entity com.gsiv.springboot.entity.base.RoleMenu
*/
@Mapper
public interface RoleMenuMapper extends BaseMapperX<RoleMenu> {


    default List<RoleMenu> selectListByRoleId(Long roleId) {
        return selectList(RoleMenu::getRoleId, roleId);
    }

    default List<RoleMenu> selectListByRoleId(Collection<Long> roleIds) {
        return selectList(RoleMenu::getRoleId, roleIds);
    }
}




