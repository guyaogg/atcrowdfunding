package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.QueryFailedException;
import com.atguigu.crowd.exception.RoleSaveException;
import com.atguigu.crowd.mapper.RoleMapper;
import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.pojo.RoleExample;
import com.atguigu.crowd.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.atguigu.crowd.constant.CrowdConstant.*;

/**
 * @author guyao
 * @create 2021-08-03 20:20
 */
@Service
public class RoleServiceImpl implements RoleService {
    Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize,Integer pageCount) {
        // 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询

        List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
        logger.info(roleList.toString());
        //封装成PageInfo
        PageInfo<Role> pageInfo = new PageInfo<>(roleList,pageCount);
        logger.info(pageInfo.toString());
        // 返回值
        return pageInfo;
    }

    @Override
    public int saveRole(Role role) {
        if(role == null || role.getName() == null || role.getName().length() == 0)
            throw new RoleSaveException(MESSAGE_OBJECT_INSERT_DATE.getStr());
        int roleCount = roleMapper.insert(role);
        return roleCount;
    }

    @Override
    public int updateRole(Role role) {
        if(role == null || role.getName() == null || role.getName().length() == 0)
            throw new RoleSaveException(MESSAGE_OBJECT_MODIFY_DATE.getStr());
        int update = roleMapper.updateByPrimaryKeySelective(role);
        return update;
    }

    @Override
    public int removeRole(List<Integer> roleIdList) {
        if(roleIdList == null || roleIdList.size() == 0)
            throw new RoleSaveException(MESSAGE_OBJECT_DELETE_DATE.getStr());
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        // where id in(roleIdList)
        criteria.andIdIn(roleIdList);
        int delete = roleMapper.deleteByExample(roleExample);
        return delete;
    }


}
