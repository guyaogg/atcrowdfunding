package com.atguigu.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @EnableDiscoveryClient 启用发现服务功能
 * @EnableEurekaClient 启用Eureka客户端功能
 * @SpringBootApplication// 主启动类
 * @EnableCircuitBreaker 过时了，，》？？自动载入了？？？开启断路器 在2020版由Resilience4J代替Hy熔断
 * @author guyao
 */

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
public class HelloBoot {
    public static void main(String[] args) {
        SpringApplication.run(HelloBoot.class,args);
    }

//    /** 2020版删除了Hy啥的熔断器
//     * 为所有断路器提供默认配置
//     * @return
//     */
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .build());
//    }
//
//    /**
//     * 供默认配置
//     * @return
//     */
//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> slowCustomizer() {
//        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build()), "slow");
//    }
}
