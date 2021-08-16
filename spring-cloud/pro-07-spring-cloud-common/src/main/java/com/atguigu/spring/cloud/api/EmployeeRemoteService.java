package com.atguigu.spring.cloud.api;

import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import com.atguigu.spring.cloud.factory.MyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @FeignClient(value = "atguigu-provoider") 当前接口和一个Provider对应 ,value对应微服务名称
 * fallbackFactory：指定provoider不可用时提供的配用方案
 * @author guyao
 */
@FeignClient(value = "atguigu-provoider",fallbackFactory = MyFallbackFactory.class)
public interface EmployeeRemoteService {
    /**
     * 远程调用的接口方法，
     * 要求：
     *  注解地址一致
     *  方法声明一致
     *  方法参数的注解一致（不能省
     *
     * @return
     */
    @RequestMapping("/provider/get/employee/remote")
    Employee getEmployeeRemote();


    /**
     * 远程查询emp集合
     * @param keyword 关键词
     * @return
     */
    @RequestMapping("/feign/consumer/search")
    List<Employee> getEmpListRemote(@RequestParam("keyword") String keyword);

    /**
     * 降级测试接口
     * @param signal
     * @return
     */
    @RequestMapping("/provider/get/emp/with/circuit/breaker")
    public ResultEntity<Employee> getEmp(@RequestParam("signal") String signal);
}
