package com.pf.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pf.pojo.Brand;
import com.pf.pojo.PageBean;
import com.pf.service.BrandService;
import com.pf.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {

    private final BrandService brandService = new BrandServiceImpl();

    /* 获取请求体数据 */
    private static String getData(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        return br.readLine();
    }

    /* 发送操作成功信息 */
    private static void responseSuccess(HttpServletResponse response) throws IOException {
        Map<String, String> res = new HashMap<>();
        res.put("status", "200");
        res.put("message", "success");
        String s = JSON.toJSONString(res);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /* 添加 */
    public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String line = getData(request);
        Brand brand = JSON.parseObject(line, Brand.class);
        brandService.insert(brand);
        responseSuccess(response);
    }

    /* 批量删除 */
    public void delByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String line = getData(request);
        int[] ids = JSON.parseObject(line, int[].class);
        brandService.delByIds(ids);
        responseSuccess(response);
    }

    /* 单个删除 */
    public void delById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        brandService.delById(id);
        responseSuccess(response);
    }

    /* 查询 */
    public void selectByTags(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int size = Integer.parseInt(request.getParameter("size"));
        String data = getData(request);
        Brand brand = JSON.parseObject(data, Brand.class);
        PageBean<Brand> pageBean = brandService.selectByTags(brand, currentPage, size);
        String s = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(s);
    }

    /* 修改 */
    public void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String data = getData(request);
        Brand brand = JSON.parseObject(data, Brand.class);
        brandService.modify(brand);
        responseSuccess(response);
    }
}
