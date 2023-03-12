package com.pf.service.impl;

import com.pf.dao.mapper.CountryMapper;
import com.pf.pojo.Country;
import com.pf.service.CountryService;
import com.pf.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;

public class CountryServiceImpl implements CountryService {
    @Override
    public ArrayList<Country> selectCountry() {
        SqlSession sqlSession = MybatisUtil.getSqlSession();
        CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
        ArrayList<Country> countries = mapper.selectCountry();
        sqlSession.close();
        return countries;
    }
}
