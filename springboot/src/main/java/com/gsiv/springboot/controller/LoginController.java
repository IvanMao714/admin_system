package com.gsiv.springboot.controller;

import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @CrossOrigin(origins = {"*"})
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
        System.out.println(11111);
        return loginService.register(user.getUsername(), user.getPassword(), user.getEmail());
    }

}
