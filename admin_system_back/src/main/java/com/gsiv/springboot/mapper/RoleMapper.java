package com.gsiv.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gsiv.springboot.entity.base.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.mapper
 * @InterfaceName: RoleMapper
 * @author: Ivan Mao
 * @description: TODO
 * @date: 2023-08-12 23:42
 * @version: 1.0
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRolesByUserId(Long id);
}
