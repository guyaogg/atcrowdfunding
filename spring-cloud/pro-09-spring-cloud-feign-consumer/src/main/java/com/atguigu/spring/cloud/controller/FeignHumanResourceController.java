package com.atguigu.spring.cloud.controller;

import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author guyao
 */

@RestController
public class FeignHumanResourceController {
    /**
     * 自动装配远程微服务的接口，后面直接使用
     */
    @Autowired
    private EmployeeRemoteService employeeRemoteService;


    @RequestMapping("/feign/consumer/test/fallback")
    public ResultEntity<Employee> testFallback(@RequestParam("signal") String signal){
        return employeeRemoteService.getEmp(signal);
    }


    @RequestMapping("/feign/consumer/get/emp")
    public Employee getEmployeeRemote() {
        return employeeRemoteService.getEmployeeRemote();
    }
    @RequestMapping("/feign/consumer/search")
    public List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword){
        return employeeRemoteService.getEmpListRemote(keyword);
    }
}
