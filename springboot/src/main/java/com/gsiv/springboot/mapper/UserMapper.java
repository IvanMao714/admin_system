package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from T_USER")
    List<User> listUsers();


}
