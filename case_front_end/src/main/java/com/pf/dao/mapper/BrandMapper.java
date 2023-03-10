package com.pf.dao.mapper;

import com.pf.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface BrandMapper {
    /* 添加方法 */
    void insert(Brand brand);
    /* 批量删除方法 */
    void delByIds(@Param("ids") int[] ids);
    /* 查询方法 */
    ArrayList<Brand> selectByTags(@Param("brand") Brand brand, @Param("begin") int begin, @Param("size") int size);
    /* 查询数量方法 */
    int selectTotalCountByTags(Brand brand);
    /* 修改方法 */
    void modifyById(Brand brand);
    /* 单个删除 */
    void delById(@Param("id") int id);
}
