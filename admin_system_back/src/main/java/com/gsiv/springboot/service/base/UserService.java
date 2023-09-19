package com.gsiv.springboot.service.base;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.base.User;

public interface UserService extends IService<User> {
    User getByUserName(String userName);

    ResponseResult login(User user);

    ResponseResult logout();

    ResponseResult register(String username, String password, String email);
}
