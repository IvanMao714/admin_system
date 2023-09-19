package com.gsiv.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.config
 * @className: InterceptorConfig
 * @author: 65151
 * @description: TODO
 * @date: 2023-09-05 17:06
 * @version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    private HandlerInterceptor httpRequestInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpRequestInterceptor)
                .addPathPatterns("/note/**","/note");
//                .addPathPatterns("/api/delnote/**", "/api/delnote")
//                .addPathPatterns( "/api/file", "/api/file/**")
//                .addPathPatterns("/api/user/**")
//                .addPathPatterns("/api/**/**");
    }

}