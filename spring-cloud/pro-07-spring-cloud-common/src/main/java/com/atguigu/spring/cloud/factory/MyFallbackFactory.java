package com.atguigu.spring.cloud.factory;

import com.atguigu.spring.cloud.api.EmployeeRemoteService;
import com.atguigu.spring.cloud.entity.Employee;
import com.atguigu.spring.cloud.entity.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实现Consumer端服务降级功能
 * 实现FallbackFactory实现@FeignClient(value = "atguigu-provoider")接口，当调用失败时调用重写方法
 * @Component 将对象加入IOC容器
 * @author guyao
 */
@Component
public class MyFallbackFactory implements FallbackFactory<EmployeeRemoteService> {
    @Override
    public EmployeeRemoteService create(Throwable throwable) {
        return new EmployeeRemoteService() {
            @Override
            public Employee getEmployeeRemote() {
                return null;
            }

            @Override
            public List<Employee> getEmpListRemote(String keyword) {
                return null;
            }

            @Override
            public ResultEntity<Employee> getEmp(String signal) {
                return ResultEntity.failed(throwable.getMessage());
            }
        };
    }
}
