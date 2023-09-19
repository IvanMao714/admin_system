package com.gsiv.springboot.service.base.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsiv.springboot.domin.LoginUser;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.base.User;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.mapper.RoleMapper;
import com.gsiv.springboot.mapper.UserMapper;
import com.gsiv.springboot.service.base.UserService;
import com.gsiv.springboot.utils.JwtUtil;
import com.gsiv.springboot.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.base.impl
 * @className: UserServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 15:32
 * @version: 1.0
 */

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuServiceImpl menuService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    public User getByUserName(String username) {
        return getOne(new QueryWrapper<User>().eq("user_name",username));
    }

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //认证没通过，给出提示
        if(Objects.isNull(authentication)){
//            throw new RuntimeException("登录失败");
            return new ResponseResult(403, "登录失败");
        }
        //通过，使用userid生成jwt jwt存入ResponseResult
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //完整信息存入redis， userid作为key
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject(userId,loginUser,2400,MINUTES);

        // 获取当前用户拥有的权限菜单
        // TODO: 获取角色
        // 获取菜单权限
        log.info(userId+":登录访问");
//        log.info(loginUser.getAuthorities().toString());
        return new ResponseResult(200, "登录成功", map);
    }


    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login" + userid);

        return new ResponseResult(200, "注销成功");
    }
//TODO: 封装User
    @Override
    public ResponseResult register(String username, String password, String email) {
        Date date = new Date();
        password = passwordEncoder.encode(password);
        User user = userMapper.findByUsername(username);
        //如果查询不到数据就通过抛出异常来给出提示
        if(!(Objects.isNull(user))){
            return new ResponseResult(403, "用户已存在");
        }
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq("user_name", username);
//        System.out.println(userMapper);
//        if ((userMapper.getOne(wrapper)) != null){
//            return new ResponseResult(403, "用户已存在");
//        }
        user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreateTime(date);
        user.setCreateBy(00000L);

        userMapper.insert(user);
        return new ResponseResult(200, "注册成功");
    }

}
