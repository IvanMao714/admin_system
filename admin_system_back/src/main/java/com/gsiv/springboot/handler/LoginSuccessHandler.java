package com.gsiv.springboot.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.handler
 * @className: LoginSuccessHandler
 * @author: 65151
 * @description: 未使用！！！
 * @date: 2023-08-05 1:02
 * @version: 1.0
 */
@Deprecated
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        System.out.println("认证成功了");
        String username=authentication.getName(); // 获取用户名

    }
}
