package com.gsiv.springboot.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.utils.JwtUtil;
import com.gsiv.springboot.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.filter
 * @className: HttpRequestInterceptor
 * @author: 65151
 * @description: 拦截Http请求，设置用户名称
 * @date: 2023-09-05 17:01
 * @version: 1.0
 */
@Component
@Slf4j
public class HttpRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String token = request.getHeader("token");
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
        } catch (Exception e) {
            return false;
        }
//        if (StringUtils.isBlank(username)){
//
//        }
        ThreadLocalUtil.setUserid(userid);
        // TODO log params
//          log.info("request header: {} ", userid);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        ThreadLocalUtil.clearUserid();
    }
}