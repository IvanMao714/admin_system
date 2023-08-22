package com.gsiv.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gsiv.springboot.entity.Role;
import org.apache.ibatis.annotations.Mapper;

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

}
