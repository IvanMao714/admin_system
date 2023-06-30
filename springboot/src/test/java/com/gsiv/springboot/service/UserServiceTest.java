package com.gsiv.springboot.service;

import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.mapper.UserMapper;
import com.gsiv.springboot.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service
 * @className: UserServiceTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 16:19
 * @version: 1.0
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void ListTest() {
        List<User> user = userService.listUsers();
        user.forEach(System.out::println);
    }

    @Test
    void LoadbynameTest() {
        User user = userService.loadUsername("ivan120714");
        System.out.println(user.toString());
    }

}
