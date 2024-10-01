package com.demo.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
// 使用 @Configuration 注解来标记一个类作为配置类。
@Configuration
// @ConfigurationProperties 注解用于将配置文件中的属性映射到Java对象的字段上。
@ConfigurationProperties(prefix = "demo.person")
public class Person {
    private String name;
    private Integer age;
    private String sex;
}
