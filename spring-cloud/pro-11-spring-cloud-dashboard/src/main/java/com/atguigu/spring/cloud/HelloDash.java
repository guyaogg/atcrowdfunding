package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @EnableHystrixDashboard 开启仪表盘功能
 * @author guyao
 */
@EnableHystrixDashboard
@SpringBootApplication
public class HelloDash {
    public static void main(String[] args) {
        SpringApplication.run(HelloDash.class,args);
    }
}
