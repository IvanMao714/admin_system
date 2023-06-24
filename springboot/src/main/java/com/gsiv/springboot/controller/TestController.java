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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String loginPage(){
        return "This test";
    }

}
