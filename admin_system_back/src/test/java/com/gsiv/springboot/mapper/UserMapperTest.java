package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.base.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    void findByUsername(){
        User user = userMapper.findByUsername("iv");
        System.out.println(user.toString());
    }

    @Test
    void insertUserTest(){
        for(int i =0; i<10; i++){
            Date date = new Date(System.currentTimeMillis());
            User user = new User();
            user.setUserName("test");
            userMapper.insert(user);
        }


    }

    @Test
    void insertUserRoleMapTest(){
        userMapper.insertUserRoleMap(1688133888049721345L, 1690404290570604546L);
    }

}
