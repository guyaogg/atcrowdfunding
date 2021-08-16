package com.atguigu.crowd.mvc.controller;

import com.atguigu.crowd.exception.RoleSaveException;
import com.atguigu.crowd.pojo.Menu;
import com.atguigu.crowd.service.MenuService;
import com.atguigu.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guyao
 * @create 2021-08-04 20:43
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;
    @RequestMapping("menu/remove.json")
//    @ResponseBody
    public ResultEntity<Menu> removeMenu(@RequestParam("id")Integer id) {
        menuService.removeMenuById(id);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("menu/update.json")
    @ResponseBody
    public ResultEntity<Menu> updateMenu(Menu menu) {
        menuService.updateMenu(menu);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("menu/save.json")
//    @ResponseBody
    public ResultEntity<Menu> saveMenu(Menu menu) {
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }
    @RequestMapping("/menu/get/whole/tree.json")
//    @ResponseBody
    public ResultEntity<Menu> getWholeTreeNew() {
        // 查询所以结果
        List<Menu> menuList = menuService.getAll();
        // 声明一个变量用来存储找到的根节点
        Menu root = null;
        // 创建Map对象存储id和Menu对象，便于找到对应关系
        Map<Integer,Menu> menuMap = new HashMap<>();
        // 遍历查询结果，填充Map
        for(Menu menu : menuList){
            Integer id = menu.getId();
            menuMap.put(id, menu);
        }
        // 再次遍历，填充Map中menu的子节点
        for (Menu menu : menuList){
            Integer pid = menu.getPid();

            // 如果pid为null说明为根节点
            if(pid == null){
                root = menu; // 此时为根节点，为其赋值
                continue;// 此时找不到上一个节点
            }

            // 不为null，在Map中找到父节点
            Menu menuParent = menuMap.get(pid);
            // 父节点为null说明树形结构有误
            if(menuParent == null){
                throw new RoleSaveException("菜单数据有误！");
            }
            // 把该节点加入到父节点的Children集合属性中
            List<Menu> children = menuParent.getChildren();
            children.add(menu);

        }
        // 将组装好的root返回给浏览器
        return ResultEntity.successWithData(root);


    }
}
