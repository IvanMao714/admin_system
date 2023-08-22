package com.gsiv.springboot.controller;

/**
 * @projectName: admin_system
 * @package: com.kyvi.admin_system.controller
 * @className: LoginController
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-22 16:38
 * @version: 1.0
 */

import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.Menu;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    MenuServiceImpl menuService;

    @Autowired
    MenuMapper menuMapper;
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test')")
    public String loginPage(){
        return "This test";
    }


    @GetMapping("/menu")
    public ResponseResult menuList(){
        ResponseResult result = new ResponseResult(200,menuService.getMenuList(menuMapper.selectMenuIdByUserId(1688133888049721345L)));
        return result;
    }
}
