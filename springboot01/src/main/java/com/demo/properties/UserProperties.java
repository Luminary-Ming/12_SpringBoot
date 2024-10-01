package com.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "user.property")
public class UserProperties {
    private String userName;
    private Integer age;
    private Integer sex;
    private List<String> likes;
}
