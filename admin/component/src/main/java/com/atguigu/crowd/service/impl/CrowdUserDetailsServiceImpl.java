package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.mapper.AssignMapper;
import com.atguigu.crowd.mvc.config.SecurityAdmin;
import com.atguigu.crowd.pojo.Admin;
import com.atguigu.crowd.pojo.AdminExample;
import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/** 实现UserDetailsService接口让登录经过数据库，并添加角色和权限
 * @author guyao
 */
@Service
public class CrowdUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AssignMapper assignMapper;
    @Autowired
    private AssignService AssignService;
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据账号查询Admin对象
        AdminExample adminExample = new AdminExample();
        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(username);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        Admin admin = adminList.get(0);
        // 获取用户id
        Integer adminId = admin.getId();
        // 获取角色集合
        List<Role> assignedRoleList = AssignService.getAssignedRole(adminId);
        // 获取权限集合
        List<String> assignAuthList = assignMapper.getRoleAuthNameById(adminId);
        // 建集合对象用来存储所需东西
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 遍历存入角色信息
        for(Role role : assignedRoleList){
           String roleName = CrowdConstant.ROLE_ +role.getName();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        // 遍历存入权限信息
        for(String authName : assignAuthList){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
            authorities.add(simpleGrantedAuthority);
        }
        // 我想给tom顶级管理方便测试
        if(username.equals("tom")){
            String roleName = CrowdConstant.ROLE_ + CrowdConstant.ADMIN_TOM.getStr();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
            authorities.add(simpleGrantedAuthority);
        }
        // 封装到SecurityAdmin
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);

        return securityAdmin;
    }
}
