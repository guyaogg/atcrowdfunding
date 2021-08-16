package com.atguigu.spring.cloud.controller;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guyao
 * @RestController 远程接口类
 */
@Slf4j
@RestController
public class EmployeeHandler {


    @HystrixCommand(fallbackMethod = "getEmpBackup")
    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Employee> getEmp(@RequestParam("signal") String signal) throws InterruptedException {
        String quickBang = "quick-bang";
        String slowBang = "slow-bang";
        if (quickBang.equals(signal)) {
            throw new RuntimeException();
        }
        if (slowBang.equals(signal)) {
            Thread.sleep(5000);
        }

        return ResultEntity.successWithData(new Employee(666, "empName66", 66.666));
    }

    public ResultEntity<Employee> getEmpBackup(@RequestParam("signal") String signal) {
        String message = "方法执行失败，执行短路signal=" + signal;
        return ResultEntity.failed(message);
    }

    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployeeRemote() {


        return new Employee(555, "tom555", 555.55);
    }

    @RequestMapping("/feign/consumer/search")
    List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword) {
        log.info("keywordCCC=" + keyword);
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(33, "empName33", 333.33));
        empList.add(new Employee(44, "empName44", 444.44));
        empList.add(new Employee(55, "empName55", 555.55));
        return empList;
    }

}
