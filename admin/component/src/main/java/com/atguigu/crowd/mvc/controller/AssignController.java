package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.pojo.Auth;
import com.atguigu.crowd.pojo.Role;
import com.atguigu.crowd.service.AdminService;
import com.atguigu.crowd.service.AssignService;
import com.atguigu.crowd.service.RoleService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static com.atguigu.crowd.constant.CrowdConstant.REDIRECT;

/**
 * @author guyao
 * @create 2021-08-05 20:47
 */
@Controller
public class AssignController {
    @Autowired
    private AssignService assignService;

    @RequestMapping("assign/save/authIds/by/roleId.json")
    @ResponseBody
    public ResultEntity<String> saveAuthIdsByRoleId(@RequestBody Map<String, List<Integer>> map){
        assignService.saveAuthIdsByRoleId(map);
        return ResultEntity.successWithoutData();

    }
    @RequestMapping("assign/get/auth/by/roleId.json")
    @ResponseBody
    public ResultEntity<List<Integer>> getAuthByRoleId(@RequestParam("roleId")Integer roleId){
        List<Integer> authList = assignService.getRoleAuthById(roleId);
        return ResultEntity.successWithData(authList);

    }
    @RequestMapping("assign/get/all/auth.json")
    @ResponseBody
    public ResultEntity<List<Auth>> getAllAuth(){

        List<Auth> data =  assignService.getAll();

        return ResultEntity.successWithData(data);
    }

    // 处理角色变革表单提交
    @RequestMapping("assign/do/role/assign.html")
    public String saveRoleList(@RequestParam(value = "roleIdList",required = false)List<Integer> roleIdList,// 可以不提供请求参数，当作取消全部角色
                               @RequestParam(value = "keyword") String keyword,// 没有参数使用默认值
                               @RequestParam(value = "pageNum") Integer pageNum,
                               @RequestParam("adminId")Integer adminId){
        assignService.saveRoleList(roleIdList,adminId);


        return REDIRECT.getStr() + "/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;


    }
    @RequestMapping("assign/to/admin/role/page.html")
    public String toAssignRolePage(@RequestParam("adminId")Integer adminId,
                                   Model model){
        // 查询已分配的角色
        List<Role> AssignedRoleList =  assignService.getAssignedRole(adminId);

        // 查询未分配的角色
        List<Role> unAssignedRoleList =  assignService.getUnAssignedRole(adminId);
        // 存入模型(本质存入请求域中
        model.addAttribute("AssignedRoleList",AssignedRoleList);
        model.addAttribute("unAssignedRoleList",unAssignedRoleList);

        return "assign/admin-role";
    }
}
