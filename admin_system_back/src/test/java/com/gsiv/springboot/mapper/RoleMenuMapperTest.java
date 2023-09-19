package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.base.RoleMenu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.mapper
 * @className: RoleMenuMapperTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-16 17:22
 * @version: 1.0
 */
@SpringBootTest
public class RoleMenuMapperTest {
    @Autowired RoleMenuMapper roleMenuMapper;

    @Test
    void insertTset(){
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuId(1695800854222753793L);
        roleMenu.setRoleId(1690404290570604546l);
        roleMenuMapper.insert(roleMenu);
    }

    @Test
    void selectListByRoleIdTest(){
        List<RoleMenu> list = roleMenuMapper.selectListByRoleId(1690404290570604546l);
        for (RoleMenu roleMenu: list) {
            System.out.println(roleMenu);
        }

    }


}
