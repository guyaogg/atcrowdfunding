package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**@SpringBootApplication 启动注释
 * @EnableFeignClients 开启声明式远程方法调用：Feign
 * @author guyao
 */

@SpringBootApplication
public class HelloBoot{
    public static void main(String[] args) {
        SpringApplication.run(HelloBoot.class,args);
    }
}