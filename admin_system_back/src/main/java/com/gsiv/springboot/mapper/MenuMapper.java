package com.gsiv.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gsiv.springboot.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.mapper
 * @InterfaceName: MenuMapper
 * @author: Ivan Mao
 * @description: MenuMapper
 * @date: 2023-08-06 0:59
 * @version: 1.0
 */

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
      *@Description:
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:
     **/

    List<Long> selectMenuIdByUserId(Long id);



}
