package com.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
// 该注解表示当前实体类映射数据库中的表名
@TableName("tb_user")
public class User {
    // 标识该属性映射数据库中的主键字段，并且主键自增
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 用户名
    /*
        该注解表示当实体类的属性名和数据库表的列名不一致时，userName 映射数据库中的 user_name 字段，
        其实 @TableField 注解也可以省略，因为 mybatis-plus 底层封装了驼峰式命名规则。
    */
    @TableField("user_name")
    private String userName;
    // 密码
    private String password;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    // 性别，1男性，2女性
    private Integer sex;
    // 出生日期
    private Date birthday;
    // 创建时间
    private Date created;
    // 更新时间
    private Date updated;
    // 这个属性不映射数据库中的任何字段
    @TableField(exist = false)
    private String address;
    /*
        该注解用于实现逻辑删除的操作，表示该属性映射的字段是逻辑删除字段。
        @TableLogic 注解包含两个主要参数：value 和 delval
            value：指定逻辑未删除时的值，默认为0。这表示在数据未被删除时，逻辑删除字段的值。
            delval：指定逻辑删除时的值，默认为1。这表示在数据被删除后，逻辑删除字段的值。
        因此可以自定义逻辑删除的值：
        @TableLogic(value="-1", delval="0")
        private Integer isDel;
     */
    @TableLogic
    @TableField("is_del")
    private Integer isDel;
}
