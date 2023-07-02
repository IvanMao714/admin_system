package com.gsiv.springboot.service;

import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.User;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service
 * @InterfaceName: LoginService
 * @author: Ivan Mao
 * @description: TODO
 * @date: 2023-07-02 0:03
 * @version: 1.0
 */
public interface LoginService {


    ResponseResult login(User user);
}
