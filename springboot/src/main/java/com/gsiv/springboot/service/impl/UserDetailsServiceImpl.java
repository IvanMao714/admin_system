package com.gsiv.springboot.service.impl;

import com.gsiv.springboot.domin.LoginUser;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.impl
 * @className: UserDetailsServiceImpl
 * @author: 65151
 * @description: Security 重写实现类
 * @date: 2023-07-01 15:44
 * @version: 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;


    //Security根据loadUserByUsername进行认证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User user = userMapper.findByUsername(username);
//        System.out.println(passwordEncoder.encode(user.getPasswd()));
//        System.out.println(passwordEncoder.matches("120714", user.getPasswd()));
//
//        user.setPasswd(passwordEncoder.encode(user.getPasswd()));

        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new LoginUser(user);
    }

    //TODO: 查询对应的权限信息
}
