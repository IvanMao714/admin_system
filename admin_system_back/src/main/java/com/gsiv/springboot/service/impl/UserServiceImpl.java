package com.gsiv.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsiv.springboot.entity.Menu;
import com.gsiv.springboot.entity.Role;
import com.gsiv.springboot.entity.User;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.mapper.RoleMapper;
import com.gsiv.springboot.mapper.UserMapper;
import com.gsiv.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.service.impl
 * @className: UserServiceImpl
 * @author: 65151
 * @description: TODO
 * @date: 2023-06-24 15:32
 * @version: 1.0
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuMapper menuMapper;

//    public String getUserAuthorityInfo(Long userId) {
//        StringBuffer authority=new StringBuffer();
//        // 根据用户id获取所有的角色
//        List<Role> roleList = roleMapper.selectList(new QueryWrapper<Role>
//        ().inSql("id", "SELECT role_id FROM sys_user_role WHERE user_id=" + userId));
//        if(roleList.size()>0){
//        String roleCodeStrs = roleList.stream().map(r -> "ROLE_" +
//        r.getCode()).collect(Collectors.joining(","));
//        authority.append(roleCodeStrs);
//        }
//        // 遍历角色，获取所有菜单权限
//        Set<String> menuCodeSet=new HashSet<String>();
//        for(Role Role:roleList){
//            List<Menu> sysMenuList = menuMapper.selectList(new
//            QueryWrapper<Menu>().inSql("id", "SELECT menu_id FROM sys_role_menu WHERE  role_id=" + Role.getId()));
//            for(Menu menu:sysMenuList){
//                String perms=menu.getPerms();
//                if(StringUtil.isNotEmpty(perms)){
//                    menuCodeSet.add(perms);
//                }
//            }
//        }
//        if(menuCodeSet.size()>0){
//            authority.append(",");
//            String menuCodeStrs =
//            menuCodeSet.stream().collect(Collectors.joining(","));
//            authority.append(menuCodeStrs);
//        }
//        System.out.println("authority:"+authority.toString());
//        return authority.toString();
//        }

}
