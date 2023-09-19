package com.gsiv.springboot.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gsiv.springboot.domin.LoginUser;
import com.gsiv.springboot.entity.base.User;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.base.impl
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
    MenuMapper menuMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl userService;


    //Security根据loadUserByUsername进行认证
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        //根据用户名查询用户信息
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(wrapper);
        //如果查询不到数据就通过抛出异常来给出提示
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }

        //根据用户查询权限信息 添加到LoginUser中
        List<String> permissionKeyList =  menuMapper.selectPermsByUserId(user.getId());
        return new LoginUser(user,permissionKeyList);

    }


}
