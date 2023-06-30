package com.gsiv.springboot.service;

import com.gsiv.springboot.entity.User;

import java.util.List;

public interface UserService {

    List<User> listUsers();

    User loadUsername(String username);

}
