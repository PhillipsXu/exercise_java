package com.pf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pf.pojo.Country;
import com.pf.service.impl.CountryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/country")
public class CountryServlet extends HttpServlet {

    private final CountryServiceImpl countryService = new CountryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Country> countries = countryService.selectCountry();
        String s = JSON.toJSONString(countries);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
