package com.czl.spring.learn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.czl.spring.learn.aopanno"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
}
