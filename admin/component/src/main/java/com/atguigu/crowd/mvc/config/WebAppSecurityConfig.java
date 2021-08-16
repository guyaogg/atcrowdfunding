package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.service.impl.CrowdUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 配置 权限功能类
 *
 * @author guyao
 * @create 2021-08-07 17:38
 */
@Configuration
@EnableWebSecurity
// 启用全局安全控制（设置true）保证4个注解生效@Pre..
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {// 登录提交配置


//        builder
//                .inMemoryAuthentication()// 在内存中完成账号密码检查// 5版本后要指定密码加密方法不然也不行
//                .withUser("tom")// 指定账号密码
//                .password("$2a$10$zK4M4rLtj0dYOzx0UpwgrOFf6nolQa1MAhLMaeHce9aSRMutkOQR2")
//                .roles("ADMIN", "学徒")// 指定角色以便访问对应资源
//                .and()
//                .withUser("jerry")// 指定账号密码
//                .password("123456")
//                .authorities("UPDATE", "内门弟子")// 指定当前用户权限
//                .and()
//                .passwordEncoder(passwordEncoder)
//        ;
        builder
                .userDetailsService(userDetailsService)// 是用user封装实现权限控制
                .passwordEncoder(passwordEncoder)// 使用加盐的加密形式提高安全性
        ;// 使用数据库验证账号密码

    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {// 放行
        security
                .authorizeRequests()// 请求授权
                .antMatchers("/admin/to/login/page.html", "/static/**", "/index.jsp")// 登录页 静态 首页放行
                .permitAll()// 无条件访问
                .antMatchers("/admin/get/page.html")// 针对路径进行管理
                .access("hasAuthority('user:get') or hasRole('经理') or hasRole('顶级管理员')")// 写入角色或权限的表达式
                .anyRequest()// 任意请求
                .authenticated()// 需要登录
                .and()
                .exceptionHandling()// 开启禁止访问后转地址/请求
                .accessDeniedHandler((request, response, e) -> {
                    request.setAttribute("exception", new Exception(CrowdConstant.MESSAGE_ASSIGN_FAIL.getStr()));
                    request.getRequestDispatcher("/WEB-INF/views/error/system-error.jsp").forward(request,response);
                })// 自定义禁用请求方法

                .and()
                .csrf()// 禁用CSRF功能
                .disable()
                .formLogin()// 以表单形式登录
                .loginPage("/admin/to/login/page.html")
                .loginProcessingUrl("/do/login.html")// 指定登录表单的地址,方法为post
                .defaultSuccessUrl("/admin/to/main/page.html")// 登录成功后前往的地址
                .usernameParameter("loginAcct")// 定制提交参数名
                .passwordParameter("userPassword")
                .and()
                .logout()// 开启退出功能
                .logoutUrl("/do/logout.html")// 退出路径
                .logoutSuccessUrl("/index.jsp")// 退出成功后的地址
                .and()
        .rememberMe()

        ;

    }
}
