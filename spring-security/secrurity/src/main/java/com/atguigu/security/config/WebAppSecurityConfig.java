package com.atguigu.security.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;



/**
 * 要被扫描到
 *
 * @author guyao
 * @create 2021-08-06 19:51
 */
// 启用web环境下权限控制功能
@EnableWebSecurity
@Configuration
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    static final String USERNAME="root";
    static final String URL="jdbc:mysql://localhost:3306/project_crowd?useUnicode=true&characterEncoding=UTF-8";
    static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    static final String PASSWORD="614521hh";
    @Bean DruidDataSource getDruidDataSource(){

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(USERNAME);
        dataSource.setUrl(URL);
        dataSource.setDriverClassName(DRIVER_CLASS);
        dataSource.setPassword(PASSWORD);
        return  dataSource;
    }
    @Bean
    public JdbcTemplate getJdbcTemplate(DruidDataSource dataSource){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
//    @Scope(proxyMode = INTERFACES)
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
//    @Autowired
//    private  MyPasswordEncoder myPasswordEncoder;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {// 登录提交配置

     /*   builder
                .inMemoryAuthentication()// 在内存中完成账号密码检查// 5版本后要指定密码加密方法不然也不行
                .withUser("tom")// 指定账号密码
                .password("123456")
                .roles("ADMIN", "学徒")// 指定角色以便访问对应资源
                .and()
                .withUser("jerry")// 指定账号密码
                .password("123456")
                .authorities("UPDATE", "内门弟子")// 指定当前用户权限
        ;*/
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)// 使用加盐的加密形式提高安全性
                ;// 使用数据库验证账号密码

    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {// 路径开放配置
        security
                .authorizeRequests()// 请求授权
                .antMatchers("/index.jsp")// 指定资源
                .permitAll()// 无条件访问
                .antMatchers("/layui/**")// 指定资源
                .permitAll()
                .antMatchers("/level1/**")// 指定资源
                .hasRole("学徒")// 指定访问资源的角色
                .antMatchers("/level2/**","/level1/**")// 指定资源
                .hasAuthority("内门弟子")// 指定访问资源的权限
                .antMatchers("/level3/**")// 指定资源
                .hasRole("宗师")// 指定访问资源的角色
                .and()
                .authorizeRequests()//
                .anyRequest()// 任意请求
                .authenticated()// 需要登录
                .and()
                .formLogin()// 以表单形式登录
                .loginPage("/index.jsp")// 指定登录页面
                .loginProcessingUrl("/do/login.html")// 指定登录表单的地址,方法为post
//                .permitAll()// 登录地址也需要放行
                .usernameParameter("loginAcct")// 定制提交参数名
                .passwordParameter("userPswd")
                .defaultSuccessUrl("/main.html")// 登录成功后前往的地址
                .and()
//        .csrf()
//        .disable()// 禁用CSRF功能
                .logout()// 开启退出功能
                .logoutUrl("/do/logout.html")// 退出路径
                .logoutSuccessUrl("/index.jsp")// 退出成功后的地址
                .and()
                .exceptionHandling()// 开启访问被拒绝路径
                //.accessDeniedPage("/to/no/auth/page.html")// 拒绝页面
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    request.setAttribute("message", "抱歉！您不能访问此资源☆☆☆☆☆☆☆");
                    request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request, response);
                })// 实现接口自定义页面

                .and()
                .rememberMe()// 开启免登录用户功能// 内存版
                ;

    }
    @Test
    public void test(){

        System.out.println("$2a$10$zK4M4rLtj0dYOzx0UpwgrOFf6nolQa1MAhLMaeHce9aSRMutkOQR2".length());
    }

}
