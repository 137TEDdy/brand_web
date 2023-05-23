package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@WebServlet("/SelectByPageAndCondition")
public class SelectByPageAndCondition extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据 [curPage,pageSize] 通过get请求：”url?curPage=4&pageSize=5“
        String _curPage=req.getParameter("currentPage");
        String _pageSize=req.getParameter("pageSize");
        int curPage=Integer.valueOf(_curPage);
        int pageSize=Integer.parseInt(_pageSize);

        //获取brand信息，通过post请求的请求体：data里
        BufferedReader reader = req.getReader();
        String params=reader.readLine();
        Brand brand=JSON.parseObject(params, Brand.class);
        //3. 调用service查询
        PageBean <Brand>pageBean=brandService.selectByPageAndCondition(curPage,pageSize,brand);

        String jsonString = JSON.toJSONString(pageBean);
        //System.out.println(jsonString);
        //for(int i=0;i<pageBean.getRows().size();i++)System.out.println(pageBean.getRows().get(i).getBlob());
        //4. 写数据
        resp.setContentType("text/json;charset=utf-8"); //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}