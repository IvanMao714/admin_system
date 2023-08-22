package com.gsiv.springboot.domin;

import com.alibaba.fastjson.annotation.JSONField;
import com.gsiv.springboot.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: admin_system
 * @package: com.gsiv.springboot.domin
 * @className: LoginUser
 * @author: 65151
 * @description: 登录使用的实现类
 * @date: 2023-07-01 15:29
 * @version: 1.0
 */
@Data
@NoArgsConstructor
//@AllArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<Long> permission;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public LoginUser(User user, List<Long> permission){
        this.user = user;
        this.permission = permission;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        //把permissions中String类型的权限信息封装成SimpleGrantedAuthority对象
//        if(authorities!=null){
//            return authorities;
//        }
//        authorities = permission.stream().
//                map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//        return authorities;
//    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() { return user.getUserName(); }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
