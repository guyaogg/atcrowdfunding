package com.atguigu.spring.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author guyao
 */
@Configuration
public class SpringCloudConfig {

    /**
     * ioc容器加入RestTemplate
     * @LoadBalanced  这个注解有负载均衡的功能，访问Provider集群
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){// 远程调用方法
        return  new RestTemplate();
    }
}
