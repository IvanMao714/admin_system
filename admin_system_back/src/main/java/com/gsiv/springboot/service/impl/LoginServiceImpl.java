package com.gsiv.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gsiv.springboot.domin.LoginUser;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.Menu;
import com.gsiv.springboot.entity.Role;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.mapper.RoleMapper;
import com.gsiv.springboot.mapper.UserMapper;
import com.gsiv.springboot.service.LoginService;
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

import java.util.*;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.impl
 * @className: LoginServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-07-02 0:04
 * @version: 1.0
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

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

    //AuthenticationManager authenticate进行用户认证
    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //认证没通过，给出提示
        if(Objects.isNull(authentication)){
//            throw new RuntimeException("登录失败");
            new ResponseResult(403, "登录失败");
        }
//        System.out.println(user.toString());
        //通过，使用userid生成jwt jwt存入ResponseResult
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        System.out.println(loginUser.toString());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //完整信息存入redis， userid作为key
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        redisCache.setCacheObject("login:"+userId,jwt,30,MINUTES);

        // 获取当前用户拥有的权限菜单
        // TODO: 获取角色
        // 获取菜单权限
        List<Long> menuId = menuMapper.selectMenuIdByUserId(loginUser.getUser().getId());
        List<Menu> menuList = menuService.getMenuList(menuId);

        map.put("menuList", menuList);

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

    @Override
    public ResponseResult register(String username, String password, String email) {
        Date date = new Date(System.currentTimeMillis());
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

        userMapper.insert(user);
        return new ResponseResult(200, "注册成功");
    }


}
