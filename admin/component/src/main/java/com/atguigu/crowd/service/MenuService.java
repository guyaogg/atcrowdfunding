package com.atguigu.crowd.service;

import com.atguigu.crowd.pojo.Menu;

import java.util.List;

/**
 * @author guyao
 * @create 2021-08-04 20:43
 */
public interface MenuService {
    List<Menu> getAll();

    int saveMenu(Menu menu);

    int updateMenu(Menu menu);


    int removeMenuById(Integer id);
}
