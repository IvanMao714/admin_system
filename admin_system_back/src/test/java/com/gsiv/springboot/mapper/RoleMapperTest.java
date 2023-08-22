package com.gsiv.springboot.mapper;

import com.gsiv.springboot.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.mapper
 * @className: RoleMapperTest
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-12 23:41
 * @version: 1.0
 */
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;

    @Test
    void insertRole(){
        Date date = new Date(System.currentTimeMillis());
        Role role = new Role();
        role.setName("Creator");
        role.setStatus("0");
        role.setRoleKey("0");
        role.setCreateBy(000000000L);
        role.setCreateTime(date);


        roleMapper.insert(role);
    }


}
