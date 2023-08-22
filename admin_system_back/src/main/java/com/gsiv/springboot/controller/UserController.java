package com.gsiv.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.PageBean;
import com.gsiv.springboot.entity.Role;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.service.LoginService;
import com.gsiv.springboot.service.UserService;
import com.gsiv.springboot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.controller
 * @className: LoginController
 * @author: 65151
 * @description: TODO
 * @date: 2023-07-01 23:47
 * @version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;



    @CrossOrigin(origins = {"*"})
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
        }

    @PostMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @PostMapping("/register")
    @CrossOrigin(origins = {"*"})
    public ResponseResult register(@RequestBody User user){
        return loginService.register(user.getUserName(), user.getPassword(), user.getEmail());
    }

    @PostMapping("/list")
//    @PreAuthorize("hasAuthority('system:user:query')")
    public ResponseResult list(@RequestBody PageBean pageBean){
        System.out.println("pageBean:"+pageBean);
//        String query=pageBean.getQuery().trim();
        Page<User> pageResult = userService.page(new Page<>(pageBean.getPageNum(),pageBean.getPageSize()));
        List<User> userList = pageResult.getRecords();
//        for(SysUser user:userList){
//            // 获取角色
//            List<Role> roleList = sysRoleService.list(new QueryWrapper<SysRole>().inSql("id", "select role_id from sys_user_role where user_id=" + user.getId()));
//            // 设置角色
//            // user.setRoles(roleList.stream().map(SysRole::getName).collect(Collectors.joining(",")));
//            user.setSysRoleList(roleList);
//        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("userList",userList);
        resultMap.put("total",pageResult.getTotal());
        return new ResponseResult(200,resultMap);
    }

}
