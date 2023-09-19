package com.gsiv.springboot.service.base.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsiv.springboot.entity.base.Role;
import com.gsiv.springboot.mapper.RoleMapper;
import com.gsiv.springboot.service.base.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service.base.impl
 * @className: RoleServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-30 22:10
 * @version: 1.0
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired RoleMapper roleMapper;

    public List<Role> selectRolesByUserId(Long id){
        return roleMapper.selectRolesByUserId(id);
    }

}
