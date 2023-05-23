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
        //url的解码方式是ISO-8859-1，然后重新utf-8编码，发送给数据库
        String encode = URLEncoder.encode(brandName, "ISO-8859-1");
        String encode2 = URLEncoder.encode(companyName, "ISO-8859-1");
        String brandNameDecode = URLDecoder.decode(encode, "utf-8");
        String companyNameDecode = URLDecoder.decode(encode2, "utf-8");


        List<Brand> brands=brandService.query(brandNameDecode,companyNameDecode);
        int count=brands.size();

        //封装pageBean，因为要分页查询，记录个数；
        PageBean<Brand> pageBeans=new PageBean<>();
        pageBeans.setRows(brands);
        pageBeans.setTotalCount(count);

        String jsonString=JSON.toJSONString(pageBeans);

        //3. 写数据
        resp.setContentType("text/json;charset=utf-8"); //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
