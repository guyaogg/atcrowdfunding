package com.atguigu.crowd.service;

import com.atguigu.crowd.pojo.Auth;
import com.atguigu.crowd.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author guyao
 * @create 2021-08-06 14:05
 */
public interface AssignService {

    int saveRoleList(List<Integer> roleIdList,Integer adminId);

    List<Role> getAssignedRole(Integer adminId);

    List<Role> getUnAssignedRole(Integer adminId);

    List<Auth> getAll();

    List<Integer> getRoleAuthById(Integer roleId);

    int saveAuthIdsByRoleId(Map<String, List<Integer>> map);
}
