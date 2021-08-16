package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.exception.QueryFailedException;
import com.atguigu.crowd.mapper.AssignMapper;
import com.atguigu.crowd.mapper.AuthMapper;
import com.atguigu.crowd.pojo.Auth;
import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.atguigu.crowd.constant.CrowdConstant.MESSAGE_QUERY_FAIL;

/**处理权限管理的业务
 * @author guyao
 * @create 2021-08-06 14:05
 */
@Service
public class AssignServiceImpl implements AssignService {
    @Autowired
    AssignMapper assignMapper;
    @Autowired
    AuthMapper authMapper;
    @Override
    public int saveRoleList(List<Integer> roleIdList,Integer adminId) {
        if(adminId == null){
            throw new QueryFailedException(MESSAGE_QUERY_FAIL.getStr());
        }
        // 删去过去的关系
        int deleteOld =  assignMapper.deleteOldRoleList(adminId);
        // 判断roleIdList的值
        if(roleIdList == null || roleIdList.size() == 0)
            return deleteOld;
        // 加入新的值
        int insertNew = assignMapper.insertNewRoleList(roleIdList,adminId);
        return deleteOld + insertNew;

    }
    @Override
    public List<Role> getAssignedRole(Integer adminId) {
        if(adminId == null)
            throw new QueryFailedException(MESSAGE_QUERY_FAIL.getStr());
        List<Role> list = assignMapper.selectAssign(adminId);
        return list;
    }

    @Override
    public List<Role> getUnAssignedRole(Integer adminId) {
        List<Role> list = assignMapper.selectUnAssign(adminId);
        return list;
    }

    @Override
    public List<Auth> getAll() {
        List<Auth> authList = authMapper.selectByExample(null);
        return authList;
    }

    @Override
    public List<Integer> getRoleAuthById(Integer roleId) {
        List<Integer> authList = assignMapper.selectRoleAuthById(roleId);
        return authList;
    }


    @Override
    public int saveAuthIdsByRoleId(Map<String, List<Integer>> map) {
        // 获取roleId
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 删去过去的关联关系
        int deleteOld = assignMapper.deleteOldAuthList(roleId);
        // 获取新增的参数
        List<Integer> authIdArray = map.get("authIdArray");
        if(authIdArray == null || authIdArray.size() == 0)
            return  deleteOld;
        int insertOld = assignMapper.insertNewAuthList(roleId,authIdArray);

        return insertOld + deleteOld;
    }
}
