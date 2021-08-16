package com.atguigu.crowd.service;

import com.atguigu.crowd.pojo.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author guyao
 * @create 2021-08-01 13:44
 */
public interface AdminService {

    int saveAdmin(Admin admin);

    Integer removeAdmin(Integer id);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String password);

    PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize,Integer pageCount);

    Admin getAdminById(Integer id);

    int update(Admin admin);


}
