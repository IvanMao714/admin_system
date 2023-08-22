package com.gsiv.springboot.service;

import com.gsiv.springboot.entity.Menu;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service
 * @className: MenuServiceTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-17 12:59
 * @version: 1.0
 */
@SpringBootTest
public class MenuServiceTest {
    @Autowired
    MenuServiceImpl menuService;
    @Autowired
    MenuMapper menuMapper;
    @Test
    void getMenuListTest(){
        List<Menu> list = menuService.getMenuList(menuMapper.selectMenuIdByUserId(1688133888049721345L));
        for (Menu menu: list) {
            System.out.println(menu);
        }
    }
}
