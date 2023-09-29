package com.gsiv.springboot.controller.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.base.PageBean;
import com.gsiv.springboot.entity.base.Role;
import com.gsiv.springboot.entity.base.User;
import com.gsiv.springboot.service.base.RoleService;
import com.gsiv.springboot.service.base.UserService;
import com.gsiv.springboot.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.controller
 * @className: LoginController
 * @author: 65151
 * @description: TODO
 * @date: 2023-07-01 23:47
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;


    /**
      *@Description: 用户登录
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:
     **/
    @CrossOrigin(origins = {"*"})
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return userService.login(user);
        }

    /**
      *@Description: 用户退出
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:
     **/
    @PostMapping("/logout")
    public ResponseResult logout(){
        return userService.logout();
    }

    /**
      *@Description: 用户注册
      *@Param:
      *@return:
      *@Author: Ivan Mao
      *@Date:
     **/
    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user){
        return userService.register(user.getUserName(), user.getPassword(), user.getEmail());
    }

    /**
      *@Description: 列出所有用户
      *@Param: pageBean
      *@return: ResponseResult
      *@Author: Ivan Mao
      *@Date:
     **/
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('sys:admin:user')")
    public ResponseResult list(@RequestBody PageBean pageBean){
        String query=pageBean.getQuery().trim();
        Page<User> pageResult = userService.page(new Page<>(pageBean.getPageNum(),pageBean.getPageSize()),new QueryWrapper<User>().like(StringUtil.isNotEmpty(query), "user_name", query));
        List<User> userList = pageResult.getRecords();
        for(User user:userList){
            // 获取角色
            List<Role> roleList = roleService.selectRolesByUserId(user.getId());
//             设置角色
            // user.setRoles(roleList.stream().map(SysRole::getName).collect(Collectors.joining(",")));
            user.setRoleList(roleList);
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("userList",userList);
        resultMap.put("total",pageResult.getTotal());
        return new ResponseResult(200,resultMap);
    }

    /**
     *@Description: 根据id查询
     *@Param: id
     *@return: ResponseResult
     *@Author: Ivan Mao
     *@Date:
     **/
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('sys:admin:user')")
    public ResponseResult findById(@PathVariable(value = "id")int id){
//        Long long_id = Long.valueOf(id);
        User user = userService.getById(id);
        Map<String,Object> map=new HashMap<>();
        map.put("User",user);
        log.info("查询用户");
        return new ResponseResult(200,map);
    }


    @PostMapping("/save")
    public ResponseResult save (@RequestBody User user){
        if(user.getId() == null){
            user.setCreateTime(new Date());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        }else{
            user.setUpdateTime(new Date());
            userService.updateById(user);
        }
        return new ResponseResult<>(200,"修改成功");
    }

    @PostMapping("/checkUserName")
    @PreAuthorize("hasAuthority('sys:admin:user')")
    public ResponseResult checkUserName(@RequestBody User user){
        if(userService.getByUserName(user.getUserName())==null){
            return new ResponseResult(200, "无重复用户");
        }else{
            return new ResponseResult(500, "存在重复用户");
        }
    }

    @Transactional
    @PostMapping("/delete")
//    @PreAuthorize("hasAuthority('system:user:delete')")
    public ResponseResult delete(@RequestBody Long[] ids){
        userService.removeByIds(Arrays.asList(ids));
        //TODO：删除相关信息
//        sysUserRoleService.remove(new QueryWrapper<UserRole>().in("user_id",ids));
//        redisUtil.removeByPrex(Constant.AUTHORITY_KEY);
        return new ResponseResult(200,"删除成功");
    }


}
