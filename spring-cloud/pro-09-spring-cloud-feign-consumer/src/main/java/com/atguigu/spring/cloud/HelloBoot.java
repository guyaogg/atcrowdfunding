package com.atguigu.spring.cloud;

import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**@SpringBootApplication 启动注释
 * @EnableFeignClients 开启声明式远程方法调用：Feign
 * @EnableHystrix 开启服务降级功能
 * @author guyao
 */
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class HelloBoot {
    public static void main(String[] args) {
        SpringApplication.run(HelloBoot.class,args);
    }
}
