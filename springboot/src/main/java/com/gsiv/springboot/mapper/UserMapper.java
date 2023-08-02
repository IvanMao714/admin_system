package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from T_USER")
    List<User> listUsers();

    @Select("select * from T_USER where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into T_USER values (UUID(), #{username}, #{password}, #{email},#{date}, #{status})")
    void insertUser( String username, String password, String email, Date date, char status);





}
