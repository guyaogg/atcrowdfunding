package com.atguigu.crowd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu implements Serializable {
    // 主键
    private Integer id;

    // 父节点id
    private Integer pid;

    // 节点名称
    private String name;

    // 节点附带的url地址，是将来点击菜单项跳转的地址
    private String url;

    // 图标
    private String icon;

    // 子节点的集合，初始化，防止空指针
    private List<Menu> children;

    // 开闭状态，默认打开
    private boolean open;
    // 设置初始值
    {
        open = true;
        children = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }
}