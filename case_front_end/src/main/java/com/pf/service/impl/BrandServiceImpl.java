package com.pf.service.impl;

import com.pf.dao.mapper.BrandMapper;
import com.pf.pojo.Brand;
import com.pf.pojo.PageBean;
import com.pf.service.BrandService;
import com.pf.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class BrandServiceImpl implements BrandService {

    @Override
    public void insert(Brand brand) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.insert(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delByIds(int[] ids) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delById(int id) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByTags(Brand brand, int currentPage, int size) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * size;
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        ArrayList<Brand> brands = mapper.selectByTags(brand, begin, size);
        int count = mapper.selectTotalCountByTags(brand);
        sqlSession.close();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(count);
        return pageBean;
    }

    @Override
    public void modify(Brand brand) {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.modifyById(brand);
        sqlSession.commit();
        sqlSession.close();
    }
}
