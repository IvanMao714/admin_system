package com.gsiv.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gsiv.springboot.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from T_USER where user_name=#{username}")
    User findByUsername(String username);

    @Insert("insert into T_USER_ROLE(user_id, role_id) values(#{userId}, #{roleId})")
    void insertUserRoleMap(Long userId, Long roleId);

}
