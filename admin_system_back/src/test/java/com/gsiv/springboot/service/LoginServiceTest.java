package com.gsiv.springboot.service;

import com.gsiv.springboot.domin.ResponseResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @projectName: admin_login
 * @package: com.gsiv.springboot.service
 * @className: LoginServiceTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-07-31 20:11
 * @version: 1.0
 */
public class LoginServiceTest {

    @Autowired
    LoginServiceImpl loginService;
    @Test
    public void registerTest(){
        ResponseResult responseResult =loginService.register("tom", "123456", "651519611@qq.com");
        System.out.println(responseResult);
    }

}
