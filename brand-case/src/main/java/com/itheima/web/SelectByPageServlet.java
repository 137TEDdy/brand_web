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
import java.util.List;

@WebServlet("/SelectByPageServlet")
public class SelectByPageServlet extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.��ȡǰ������ [curPage,pageSize] ͨ��get���󣺡�url?curPage=4&pageSize=5��
        String _curPage=req.getParameter("currentPage");
        String _pageSize=req.getParameter("pageSize");
        int curPage=Integer.valueOf(_curPage);
        int pageSize=Integer.parseInt(_pageSize);
        //2. ����service��ѯ
        PageBean pageBean=brandService.selectByPage(curPage,pageSize);

        String jsonString = JSON.toJSONString(pageBean);
        System.out.println(jsonString);
//        BufferedReader br = req.getReader();
//        String params = br.readLine();//json�ַ���
//        System.out.println(params);
        //תΪBrand����
        //3. д����
        resp.setContentType("text/json;charset=utf-8"); //��֪�������Ӧ��������ʲô�� ��֪�����ʹ��ʲô�ַ������н���
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}