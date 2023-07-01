package com.gsiv.springboot.service.impl;

import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.mapper.UserMapper;
import com.gsiv.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.impl
 * @className: UserServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 15:32
 * @version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> listUsers() {
        return userMapper.listUsers();
    }


}
