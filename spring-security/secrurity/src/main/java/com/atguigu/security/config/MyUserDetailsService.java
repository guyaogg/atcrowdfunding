package com.atguigu.security.config;

import com.atguigu.security.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guyao
 * @create 2021-08-07 14:11
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    // 总目标使用数据库数据验证用户名密码
    @Override
    public UserDetails loadUserByUsername(
            // 表单提交的用户名
            String username) throws UsernameNotFoundException {
        // 查询admin
        String sql = "select * from t_admin where login_acct = ?";
        List<Admin> query = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Admin.class), username);
        Admin admin = query.get(0);
        // 给admin加权限或角色
        List<GrantedAuthority> authorityLIst = new ArrayList<>();
        authorityLIst.add(new SimpleGrantedAuthority("ROLE_ADMIN"));// 角色
        authorityLIst.add(new SimpleGrantedAuthority("内门弟子"));// 权限
        System.out.println(admin);

        // 封装成user
        User user = new User(admin.getLogin_acct(), admin.getUser_pswd(), authorityLIst);
        return user;
    }
}
