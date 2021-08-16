package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.RemoveFailException;
import com.atguigu.crowd.pojo.Admin;
import com.atguigu.crowd.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.atguigu.crowd.constant.CrowdConstant.*;

/**
 * @author guyao
 * @create 2021-08-02 15:08
 */
@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 处理更新业务
     */
    @PostMapping("admin/update.html")
    public String toEditPage(Admin admin,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,// 没有参数使用默认值
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        int updateCount = adminService.update(admin);
        return REDIRECT.getStr() + "/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;

    }

    /**
     * 处理编辑请求
     */
    @GetMapping("/admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId") Integer id,
                             Model model) {
        // 获取admin对象
        Admin admin = adminService.getAdminById(id);
        // 存入到模型
        model.addAttribute("admin", admin);

        return ADMIN_PAGE.getStr() + EDIT_PAGE.getStr();

    }

    /**
     * 处理新增表单
     */
    @PreAuthorize("hasAuthority('user:save') or hasRole('顶级管理员')")
    @PostMapping("/admin/save.html")
    public String removeId(Admin admin) {


        adminService.saveAdmin(admin);
        return REDIRECT.getStr() + "/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    /**
     * 删除请求
     *
     * @param id
     * @param can
     * @param session
     * @return
     */
    @GetMapping("/admin/remove/{adminId}/{can}.html")
    public String removeId(@PathVariable("adminId") Integer id,
                           @PathVariable("can") String can,
                           HttpSession session
    ) {
        Admin adminById = adminService.getAdminById(id);
        Admin sessionAdmin = (Admin) session.getAttribute(ATTR_NAME_LOGIN_ADMIN.getStr());
        if (adminById.equals(sessionAdmin)) {
            throw new RemoveFailException(MESSAGE_REMOVE_FAIL.getStr());

        }


        // 会话中存储删除的数据数
        Integer removeCount = adminService.removeAdmin(id);
        session.setAttribute("removeCount", removeCount);
        Integer remove = session.getAttribute("oldRemove") == null ? 1 : (Integer) session.getAttribute("remove") + 1;

        session.setAttribute("remove", remove);
        // 请求转发刷新问题


        // 重定向
        return REDIRECT.getStr() + "/admin/get/page.html?" + can;


    }

    /**
     * 数据列表页的请求
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @param pageCount
     * @param modelMap
     * @return
     */

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "") String keyword,// 没有参数使用默认值
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "12") Integer pageSize,
            @RequestParam(value = "pageCount", defaultValue = "5") Integer pageCount,
            ModelMap modelMap) {
        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize, pageCount);
        modelMap.addAttribute(ATTR_NAME_PAGE_INFO.getStr(), pageInfo);

        return ADMIN_PAGE.getStr() + PAGE_PAGE.getStr();

    }

    @GetMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        session.invalidate();//session失效
        return REDIRECT.getStr() + XML_ADMIN.getStr() + LOGIN_PAGE.getStr() + XML_HTML.getStr();
    }

    /**
     * 登录请求
     *
     * @param loginAcct
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPassword") String password,
            HttpSession session) {
        // 调用业务(成功返回对象)
        Admin admin = adminService.getAdminByLoginAcct(loginAcct, password);

        //将登陆成功存入session中
        session.setAttribute(ATTR_NAME_LOGIN_ADMIN.getStr(), admin);

        return REDIRECT.getStr() + XML_ADMIN.getStr() + MAIN_PAGE.getStr() + XML_HTML.getStr();

    }
}
