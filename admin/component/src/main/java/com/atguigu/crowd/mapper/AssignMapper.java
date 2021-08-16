package com.atguigu.crowd.mapper;

import com.atguigu.crowd.pojo.Auth;
import com.atguigu.crowd.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author guyao
 * @create 2021-08-06 14:02
 */
public interface AssignMapper {
    int deleteOldRoleList(Integer adminId);

    int insertNewRoleList(@Param("roleIdList") List<Integer> roleIdList, @Param("adminId") Integer adminId);

    List<Role> selectAssign(Integer adminId);

    List<Role> selectUnAssign(Integer adminId);

    List<Integer> selectRoleAuthById(Integer roleId);

    int deleteOldAuthList(Integer roleId);

    int insertNewAuthList(@Param("roleId") Integer roleId, @Param("authIdArray") List<Integer> authIdArray);

    List<String> getRoleAuthNameById(Integer adminId);

}

