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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;


@WebServlet("/queryByConditionServlet")
public class queryByConditionServlet extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String brandName=req.getParameter("brandName");
        String companyName=req.getParameter("companyName");
        //url�Ľ��뷽ʽ��ISO-8859-1��Ȼ������utf-8���룬���͸����ݿ�
        String encode = URLEncoder.encode(brandName, "ISO-8859-1");
        String encode2 = URLEncoder.encode(companyName, "ISO-8859-1");
        String brandNameDecode = URLDecoder.decode(encode, "utf-8");
        String companyNameDecode = URLDecoder.decode(encode2, "utf-8");


        List<Brand> brands=brandService.query(brandNameDecode,companyNameDecode);
        int count=brands.size();

        //��װpageBean����ΪҪ��ҳ��ѯ����¼������
        PageBean<Brand> pageBeans=new PageBean<>();
        pageBeans.setRows(brands);
        pageBeans.setTotalCount(count);

        String jsonString=JSON.toJSONString(pageBeans);

        //3. д����
        resp.setContentType("text/json;charset=utf-8"); //��֪�������Ӧ��������ʲô�� ��֪�����ʹ��ʲô�ַ������н���
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
