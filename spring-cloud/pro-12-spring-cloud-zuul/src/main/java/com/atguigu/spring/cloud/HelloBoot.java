package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @EnableZuulProxy 启用zuul网关功能
 * @EnableEurekaServer// 启动注册中心
 * @SpringBootApplication// 主启动类
 * @author guyao
 */
@EnableZuulProxy
@SpringBootApplication
public class HelloBoot {
    public static void main(String[] args) {
        SpringApplication.run(HelloBoot.class,args);
    }
}