package com.demo.web;

import com.demo.pojo.Person;
import com.demo.properties.UserProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*
    @Slf4j 是一个来自 Lombok 库的注解，用于自动为 Java 类生成一个名为 log 的日志字段，
    并且这个字段是某个日志框架（如 SLF4J、Logback、Log4j 等）的日志记录器实例。
*/
@Slf4j
public class DemoController {

    @Autowired
    private UserProperties userProperties;


    //@RequestMapping(path="/hello",produces = "text/html;charset=utf-8")
    @RequestMapping("/hello")
    public String hello() {
        log.trace("追踪日志");
        log.debug("调试日志");
        // {} ：作用相当于占位符
        log.info("普通日志 {}",userProperties);
        log.warn("警告日志");
        log.error("错误日志");

        try {
            int i = 1/0;
        }catch (Exception e){
            log.error("错误日志 {}",e.getMessage());
        }

        return "" + userProperties;
    }

    @PostMapping
    public String add(Person person){
        log.info("DemoController 中的 add方法执行" );
        log.info("DemoController 中的 add 执行完成" );
        return null;
    }
}
