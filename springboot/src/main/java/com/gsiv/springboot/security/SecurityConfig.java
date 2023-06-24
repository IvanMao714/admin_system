package com.kyiv.admin_login.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 编码器
     * 注：若使用自带的编码器须在注册时对密码进行编码
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
//        web.ignoring().antMatchers("/login");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin(); // 表单登录
                // http.httpBasic() // HTTP Basic
//                .loginPage("/login.html") // 登录跳转 URL
//                .loginProcessingUrl("/login") // 处理表单登录 URL
//                .successHandler(authenticationSucessHandler) // 处理登录成功
//                .failureHandler(authenticationFailureHandler) // 处理登录失败0
//                .and()
//                .authorizeRequests() // 授权配置
//                .antMatchers("/login.html").permitAll() // 登录跳转 URL 无需认证
//                .anyRequest().permitAll();   // 所有请求
//                .authenticated() // 都需要认证
//                .and().csrf().disable();
    }


}

