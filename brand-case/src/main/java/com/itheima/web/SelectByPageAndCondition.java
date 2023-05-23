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
        //1.��ȡǰ������ [curPage,pageSize] ͨ��get���󣺡�url?curPage=4&pageSize=5��
        String _curPage=req.getParameter("currentPage");
        String _pageSize=req.getParameter("pageSize");
        int curPage=Integer.valueOf(_curPage);
        int pageSize=Integer.parseInt(_pageSize);

        //��ȡbrand��Ϣ��ͨ��post����������壺data��
        BufferedReader reader = req.getReader();
        String params=reader.readLine();
        Brand brand=JSON.parseObject(params, Brand.class);
        //3. ����service��ѯ
        PageBean <Brand>pageBean=brandService.selectByPageAndCondition(curPage,pageSize,brand);

        String jsonString = JSON.toJSONString(pageBean);
        //System.out.println(jsonString);
        //for(int i=0;i<pageBean.getRows().size();i++)System.out.println(pageBean.getRows().get(i).getBlob());
        //4. д����
        resp.setContentType("text/json;charset=utf-8"); //��֪�������Ӧ��������ʲô�� ��֪�����ʹ��ʲô�ַ������н���
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}