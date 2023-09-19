package com.gsiv.springboot.service.base.impl;

import com.gsiv.springboot.entity.base.Menu;


import com.gsiv.springboot.mapper.MenuMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.service.base.impl
 * @className: MenuServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-17 11:04
 * @version: 1.0
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@Slf4j
public class MenuServiceImpl {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuList(Collection<Long> ids) {
        return this.buildMenuTree(menuMapper.selectBatchIds(ids));
    }

    public List<Menu> buildMenuTree(List<Menu> menuList) {

        // 排序，保证菜单的有序性
        menuList.sort(Comparator.comparing(Menu::getOrderNum));

        // 构建菜单树
        List<Menu> resultMenuList = new ArrayList<>();

        for (Menu sysMenu : menuList) {
            // 判断父节点，添加到集合
            if (sysMenu.getParentId() == 0L) {
                resultMenuList.add(sysMenu);

                // 寻找子节点
                for (Menu e : menuList) {
//                Long parent = e.getParentId();
//                Long child = sysMenu.getId();
                    if (e.getParentId().equals(sysMenu.getId())) {
                        sysMenu.getChildren().add(e);
                    }
                }


            }

        }
        return resultMenuList;
    }
}