package com.gsiv.springboot.controller;

import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.controller
 * @className: UserController
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 15:47
 * @version: 1.0
 */

//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    @GetMapping("/list")
//    public List<User> listUsers(){
//        return userService.listUsers();
//    }
//}
