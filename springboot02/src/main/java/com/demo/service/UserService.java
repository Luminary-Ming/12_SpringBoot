package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.pojo.User;

import java.util.List;

/*
    IService<User> 同样也是 Mybatis-Plus 框架中提供的一个泛型接口
 */
public interface UserService extends IService<User> {
    // 查询所有用户
    public List<User> findAll();

    // 条件查询
    public List<User> findByCondition(String name, Integer sex, Integer age);

    // 删除用户
    public Integer deleteUser(String name);

    // 更新用户
    public Integer updateUser(User user);

    // 分页查询数据
    public Result<PageResult<User>> findUserByPage(Integer pageNum, Integer pageSize);

}
