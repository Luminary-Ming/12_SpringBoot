package com.demo;

import com.demo.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/*
    @SpringBootApplication 注解是用于标记 SpringBoot 应用的启动类。
    它也是一个复合注解，整合了以下三个注解的功能：
      1. @Configuration：标识该类是配置类，表明一个类声明了一个或多个 @Bean 方法，并且这些方法被 Spring 容器管理。
      2. @EnableAutoConfiguration：启用 SpringBoot 的自动配置。
      3. @ComponentScan：启用组件扫描功能，自动扫描包中的组件，包括 @Component、@Service、@Repository、@Controller 等注解。

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
