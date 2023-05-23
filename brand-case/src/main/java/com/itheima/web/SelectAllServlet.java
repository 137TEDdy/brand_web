package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/selectAll")
public class SelectAllServlet extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. ����service��ѯ
        List<Brand> brands = brandService.selectAll();
        //2. תΪJSON
        String jsonString = JSON.toJSONString(brands);
        //3. д����
        resp.setContentType("text/json;charset=utf-8"); //��֪�������Ӧ��������ʲô�� ��֪�����ʹ��ʲô�ַ������н���
        resp.getWriter().write(jsonString);
        for(int i=0;i<brands.size();i++)System.out.println(brands.get(i).getBlob());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
