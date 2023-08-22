package com.gsiv.springboot.service;

import com.gsiv.springboot.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.testng.annotations.Test;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service
 * @className: UserDetailsServiceTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-07-01 15:49
 * @version: 1.0
 */

@SpringBootTest
public class UserDetailsServiceTest {
    @Autowired
    UserDetailsServiceImpl userService;
    //null 的原因是被Security使用了

    @Test
    void LoadByNameTest() {
        UserDetails user = userService.loadUserByUsername("ivan");
        System.out.println(user.toString());
    }

}
