package com.gsiv.springboot.entity;

import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


//@Data 减少模板代码

@Data
public class User implements Serializable{
//一个类只有实现了 Serializable 接口，它的对象才是可序列化的  ->  从对象到 Json 字符串的转换过程就是序列化的过程
private static final long serialVersionUID = 3497935890426858541L;

	private String id;

	private String username;

	private String password;

	private String email;

	private Date create_time;

	private char status;



	private boolean accountNonExpired = true;

	private boolean accountNonLocked= true;

	private boolean credentialsNonExpired= true;

	private boolean enabled= true;



	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", createTime=" + create_time +
				", status=" + status +
				", accountNonExpired=" + accountNonExpired +
				", accountNonLocked=" + accountNonLocked +
				", credentialsNonExpired=" + credentialsNonExpired +
				", enabled=" + enabled +
				'}';
	}
}

