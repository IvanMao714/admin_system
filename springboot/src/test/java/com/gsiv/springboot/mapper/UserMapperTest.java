package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.mapper
 * @className: UserMapperTet
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 16:02
 * @version: 1.0
 */

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void ListTest() {
        List<User> users = userMapper.listUsers();
        for(User user : users) {
            System.out.println(user.toString());
        }
    }

}
