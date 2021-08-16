package com.atguigu.crowd.service;

import com.atguigu.crowd.pojo.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author guyao
 * @create 2021-08-03 20:20
 */
public interface RoleService {
    PageInfo<Role> getPageInfo(String keyword,Integer pageNum,Integer pageSize,Integer pageCount);

    int saveRole(Role role);

    int updateRole(Role role);

    int removeRole(List<Integer> roleIdList);


}
