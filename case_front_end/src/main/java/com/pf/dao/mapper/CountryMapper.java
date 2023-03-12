package com.pf.dao.mapper;

import com.pf.pojo.Country;

import java.util.ArrayList;

public interface CountryMapper {
    /* 查找地区 */
    ArrayList<Country> selectCountry();
}
