package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/DeleteByIdsServlet")
public class DeleteByIdsServlet extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收数据为[1,4,6,..]
        BufferedReader br=req.getReader();
        String params=br.readLine();
        //2.字符串转数组
        int []ids=JSON.parseObject(params,int[].class);
        //3.调用service
        brandService.deleteByIds(ids);
        //4.发送给前端
        resp.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

