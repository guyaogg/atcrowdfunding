package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.service.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author guyao
 * @create 2021-08-03 20:22
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(RoleController.class);
    // @ResponseBody
    @RequestMapping("/role/remove/array.json")// 返回json数据
    public ResultEntity<String> removeRoleArray(@RequestBody List<Integer> roleIdList){
        logger.info(roleIdList.toString());

        roleService.removeRole(roleIdList);
        return ResultEntity.successWithoutData();
    }
    // @ResponseBody
    @RequestMapping("/role/update.json")// 返回json数据
    public ResultEntity<String> updateRole(Role role){
        roleService.updateRole(role);
        return ResultEntity.successWithoutData();
    }
    // @ResponseBody
    @RequestMapping("/role/save.json")// 返回json数据
    public ResultEntity<String> saveRole(Role role){
        roleService.saveRole(role);
        return ResultEntity.successWithoutData();

    }
    @PreAuthorize("hasRole('部长') or hasRole('顶级管理员')")// 对资源进行角色或权限加锁
    // @ResponseBody
    @RequestMapping("/role/get/page/info.json")// 返回json数据
    public ResultEntity<PageInfo<Role>> getPageInfo(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                    @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                    @RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
                                                    @RequestParam(value = "pageCount",defaultValue = "5")Integer pageCount) {

        logger.info("进入rolePage查询请求了");
        // 调用service方法获得数据
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize,pageCount);
        // 返回封装好的json数据
        return ResultEntity.successWithData(pageInfo);

    }
}
