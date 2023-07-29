package com.gsiv.springboot.service.impl;

import com.gsiv.springboot.domin.LoginUser;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.service.LoginService;
import com.gsiv.springboot.utils.JwtUtil;

import com.gsiv.springboot.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

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
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    //AuthenticationManager authenticate进行用户认证
    @Override
    public ResponseResult login(User user) {


        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPasswd());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        //认证没通过，给出提示
        if(Objects.isNull(authentication)){
            throw new RuntimeException("登录失败");
        }
        System.out.println(user.toString());
        //通过，使用userid生成jwt jwt存入ResponseResult
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        System.out.println(loginUser.toString());
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //完整信息存入redis， userid作为key
        HashMap<String,String> map = new HashMap<>();
        map.put("token",jwt);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser,111111,MINUTES);
        return new ResponseResult(200, "登录成功", map);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        int userid = loginUser.getUser().getId();
        //删除redis中的值
        redisCache.deleteObject("login" + userid);

        return new ResponseResult(200, "注销成功");
    }
}
