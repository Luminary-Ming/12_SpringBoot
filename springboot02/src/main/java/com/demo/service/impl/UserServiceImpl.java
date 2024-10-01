package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.mapper.UserMapper;
import com.demo.pojo.PageResult;
import com.demo.pojo.Result;
import com.demo.pojo.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/*
    ServiceImpl<UserMapper, User> 是一个抽象类中，它实现了 IService<User> 接口。
    所以在使用 Mybatis-Plus 框架时，UserService 接口的的实现类 UserServiceImpl 要去继承 ServiceImpl<UserMapper, User> 抽象类。
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    // 查询所有用户
    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    // 条件查询
    @Override
    public List<User> findByCondition(String name, Integer sex, Integer age) {
        // 创建查询条件构造器
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*
            isEmpty() 与 isBlank() 的区别：
              ● isEmpty() ：包括 null、""
              ● isBlank() ：包括 null、""、"   "（空白字符）

            like(StringUtils.isNotBlank(name), "name", name) 中：
              ● 如果 StringUtils.isNotBlank(name) 值为 true，则拼接上 like '%#{name}%'
              ● 如果 StringUtils.isNotBlank(name) 值为 false，则不会拼接上 like '%#{name}%'

            select * from tb_user where name like '%#{name}%' or sex = #{sex} or age > #{age}
        */
        wrapper
                .like(StringUtils.isNotBlank(name), "name", name)
                .or()
                .eq(sex != null, "sex", sex)
                .or()
                .gt(age != null, "age", age);
        return userMapper.selectList(wrapper);
    }

    // 删除用户
    @Override
    public Integer deleteUser(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // delete from tb_user where id = 10
        wrapper.eq("id",10);
        return userMapper.delete(wrapper);
    }

    // 更新用户
    @Override
    public Integer updateUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // update tb_user set...... where id = 5
        wrapper.eq("id",5);
        return userMapper.update(user,wrapper);
    }

    // 分页查询数据
    @Override
    public Result<PageResult<User>> findUserByPage(Integer pageNum, Integer pageSize) {
        // 创建分页对象
        Page<User> page = new Page<>(pageNum,pageSize);
        userMapper.selectList(page,null);
        // 创建分页结果对象
        PageResult<User> pageResult = new PageResult<>();
        // 设置分页结果对象的属性值，值从分页对象的属性值中获得
        pageResult.setTotal(page.getTotal());
        pageResult.setDatas(page.getRecords());
        return Result.success(pageResult);
    }

    public Integer updateUser2(User user) {
        // 创建更新条件构造器
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        // update tb_user set name = #{name}, password = #{password}, age = #{age}, sex = #{sex} where id = #{id}
        wrapper
                .set("name",user.getName())
                .set("password",user.getPassword())
                .set("age",user.getAge())
                .set("sex",user.getSex())
                .eq("id",user.getId());
        return userMapper.update(wrapper);
    }

    public Integer updateUser3(User user){
        // 创建基于 Lambda 表达式的更新条件构造器
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        // User::getName 指向 User 对象中的 name 映射的数据库字段
        wrapper
                .set(User::getName,user.getName())
                .set(User::getPassword,user.getPassword())
                .set(User::getAge,user.getAge())
                .set(User::getSex,user.getSex())
                .eq(User::getId,user.getId());
        return userMapper.update(wrapper);
    }

    public List<User> findByCondition2(String name, Integer sex, Integer age) {
        // 创建基于 Lambda 表达式的查询条件构造器
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(StringUtils.isNotBlank(name),User::getName,name)
                .or()
                .eq(sex!=null , User::getSex,sex)
                .or()
                .gt(age!=null,User::getAge,age);

        List<User> users = this.userMapper.selectList(wrapper);
        return users;
    }

}
