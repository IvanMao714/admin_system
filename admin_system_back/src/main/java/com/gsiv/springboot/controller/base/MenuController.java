package com.gsiv.springboot.controller.base;

import com.gsiv.springboot.domin.ResponseResult;
import com.gsiv.springboot.entity.base.Menu;
import com.gsiv.springboot.mapper.MenuMapper;
import com.gsiv.springboot.service.base.impl.MenuServiceImpl;
import com.gsiv.springboot.utils.JwtUtil;
import com.gsiv.springboot.utils.RedisCache;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: admin_system_back
 * @package: com.gsiv.springboot.controller
 * @className: MenuController
 * @author: 65151
 * @description: TODO
 * @date: 2023-08-24 15:08
 * @version: 1.0
 */
@RestController
@RequestMapping("/api/menu")
@Slf4j
public class MenuController {

    @Autowired RedisCache redisCache;

    @Autowired MenuMapper menuMapper;

    @Autowired MenuServiceImpl menuService;
    @GetMapping("/list")
    public ResponseResult getMenuListByToken(@RequestHeader("token") String jwt){
        //解析token
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(jwt);
            userid = claims.getSubject();
        } catch (Exception e) {
            return new ResponseResult(500, "非法token验证");
        }
        Long long_id = Long.valueOf(userid);
        List<Long> menuId = menuMapper.selectMenuIdByUserId(long_id);
        List<Menu> menuList = menuService.getMenuList(menuId);
        log.info(userid +":获取菜单列表");
        return new ResponseResult(200, "获取列表成功",menuList);

    }


}
