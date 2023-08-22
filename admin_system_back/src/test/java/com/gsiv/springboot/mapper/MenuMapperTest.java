package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.mapper
 * @className: MenuMapperTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-07 0:18
 * @version: 1.0
 */
@SpringBootTest
public class MenuMapperTest {
    @Autowired
    MenuMapper menuMapper;
    @Test
    void InsertTest(){
        Menu menu = new Menu();
        menu.setMenuName("用户管理");
        menu.setIcon("icon-setting");
        menu.setStatus("0");
        menu.setParentId(1690655788755935234L);
        menu.setOrderNum(9999);
        menu.setMenuType("C");
        menu.setRoutePath("/adminTime");
        menu.setParentId(0L);
        menuMapper.insert(menu);

    }

    @Test
    void selectPermsByUserIdTest(){
        String pram = menuMapper.selectMenuIdByUserId(1688133888049721345L).toString();
        System.out.println(pram);

    }

}
