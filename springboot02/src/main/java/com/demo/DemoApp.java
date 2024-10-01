package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 扫描 Mapper 接口
@MapperScan("com.demo.mapper")
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class,args);
    }
}
/*
    在 SpringBoot 项目中，如果配置了 application.properties 和 application.yaml 两个配置文件，
    相同的配置以 application.properties 配置文件为主，不同的配置两者合并。
 */
