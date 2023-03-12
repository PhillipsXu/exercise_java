package com.pf.service;

import com.pf.pojo.Brand;
import com.pf.pojo.PageBean;

public interface BrandService {
    void insert(Brand brand); // 新增
    void delByIds(int[] ids); // 批量删除
    void delById(int id); // 单个删除
    PageBean<Brand> selectByTags(Brand brand, int currentPage, int size); // 返回查询对象
    void modify(Brand brand); // 修改
}
