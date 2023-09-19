package com.gsiv.springboot.service.base;

import com.gsiv.springboot.entity.base.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Ivan Mao
* @description 针对表【sys_role】的数据库操作Service
* @createDate 2022-08-30 08:23:22
*/

public interface RoleService extends IService<Role> {
    List<Role> selectRolesByUserId(Long id);

}
