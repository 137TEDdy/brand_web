package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SelectWithStatusServlet ")
public class SelectWithStatusServlet  extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据 [curPage,pageSize] 通过get请求：”url?curPage=4&pageSize=5“
        String _curPage=req.getParameter("currentPage");
        String _pageSize=req.getParameter("pageSize");
        int curPage=Integer.valueOf(_curPage);
        int pageSize=Integer.parseInt(_pageSize);
        //2. 调用service查询
        PageBean pageBean=brandService.selectByPageAndStatus(curPage,pageSize);

        String jsonString = JSON.toJSONString(pageBean);
        System.out.println(jsonString);
//        BufferedReader br = req.getReader();
//        String params = br.readLine();//json字符串
//        System.out.println(params);
        //转为Brand对象
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8"); //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}