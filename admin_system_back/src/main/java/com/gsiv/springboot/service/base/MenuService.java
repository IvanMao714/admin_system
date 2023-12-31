package com.gsiv.springboot.service.base;

import com.gsiv.springboot.entity.base.Menu;

import java.util.Collection;
import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service
 * @InterfaceName: MenuService
 * @author: Ivan Mao
 * @description: TODO
 * @date: 2023-08-15 11:02
 * @version: 1.0
 */
public interface MenuService {
    List<Menu> getMenuList(Collection<Long> ids);
}
