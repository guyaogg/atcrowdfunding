package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.JSONException;
import com.atguigu.crowd.exception.RoleSaveException;
import com.atguigu.crowd.mapper.MenuMapper;
import com.atguigu.crowd.pojo.Menu;
import com.atguigu.crowd.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guyao
 * @create 2021-08-04 20:43
 */
@Service
public class MenuServiceImpl implements MenuService {
    Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuMapper menuMapper;
    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(null);
    }

    @Override
    public int saveMenu(Menu menu) {
        // 防止传入空数据
        judgeNull(menu);
        // 保存数据库
        int insert = menuMapper.insert(menu);
        return insert;
    }

    @Override
    public int updateMenu(Menu menu) {
        // 防止传入空数据
        judgeNull(menu);
        // 更新数据库(由于pid没有传入，故使用有条件的更新
        int update = menuMapper.updateByPrimaryKeySelective(menu);
        return update;
    }

    @Override
    public int removeMenuById(Integer id) {
        if(id == null)
            throw new JSONException(CrowdConstant.MESSAGE_OBJECT_DELETE_DATE.getStr());
        if(id == 1)
            throw new JSONException(CrowdConstant.MESSAGE_REMOVE_FAIL.getStr());
        int delete = menuMapper.deleteByPrimaryKey(id);
        return delete;
    }


    private void judgeNull(Menu menu){
        if(menu == null || menu.getIcon() == null || menu.getName() == null || menu.getUrl() == null||
                menu.getIcon().equals("") || menu.getName().equals("") || menu.getUrl().equals("")){
            logger.info("传空值了"+ menu);
            throw new RoleSaveException(CrowdConstant.MESSAGE_OBJECT_INSERT_DATE.getStr());
        }

    }
}
