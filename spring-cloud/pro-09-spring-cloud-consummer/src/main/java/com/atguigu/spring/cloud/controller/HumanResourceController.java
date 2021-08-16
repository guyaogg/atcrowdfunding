package com.atguigu.spring.cloud.controller;

import com.atguigu.spring.cloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author guyao
 */
@RestController
public class HumanResourceController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/ribbon/get/employee")
    public Employee getEmployeeRemote() {
        // 声明远程微服务的主机地址加端口号
        // String host = "http://localhost:1000";
        // 从“ip地址+端口号” 改为 “微服务名称”
        String host = "http://atguigu-provoider";
        // 声明要具体调用功能的url地址
        String url = "/provider/get/employee/remote";
        // 通过RestTemplate远程调用微服务方法

        return restTemplate.getForObject(host + url, Employee.class);
    }
}
