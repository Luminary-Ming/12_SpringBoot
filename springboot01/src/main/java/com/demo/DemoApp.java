package com.demo;

import com.demo.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/*
    在 SpringBoot 项目中 application.properties 配置比 application.yaml 配置优先级高
*/
@SpringBootApplication
@Slf4j
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class,args);

/*
        -在启动 springboot 项目的时候，如果需要设置一些参数，需要得到 springboot 的核心对象 SpringApplication
        SpringApplication application = new SpringApplication(DemoApp.class);

        -不显示banner
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);

        -通过SpringApplicationBuilder 进行springboot项目的启动
        new SpringApplicationBuilder()
                .sources(DemoApp.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
*/
    }
}
