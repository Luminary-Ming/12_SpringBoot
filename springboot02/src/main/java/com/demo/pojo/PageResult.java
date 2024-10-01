package com.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    // 查询总记录数
    private Long total;
    // 查询当前页面显示的数据
    private List<T> datas;
}
