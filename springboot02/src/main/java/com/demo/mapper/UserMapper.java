package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.pojo.User;
/*
    BaseMapper<User> 是 MyBatis-Plus 框架中提供的一个泛型接口，它主要用于简化数据访问层（dao 层）的开发，
    它提供了一系列基础的CRUD（create、retrieve、update、delete）操作方法。
    BaseMapper<User>接口提供了一系列基础的方法，包括但不限于：
        ● insert     ：插入一条记录。
        ● updateById ：根据主键更新记录。
        ● selectById ：根据主键查询记录。
        ● deleteById ：根据主键删除记录。
        ● selectList ：查询所有记录的列表。
        ● selectPage ：分页查询记录。
 */
public interface UserMapper extends BaseMapper<User> {
}
