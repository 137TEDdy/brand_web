package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. ����Ʒ������
        BufferedReader br = req.getReader();
        String params = br.readLine();//json�ַ���
        //תΪBrand����
        Brand brand = JSON.parseObject(params,Brand.class);
        //2. ����service���
        int num=brandService.add(brand);
        //3. ��Ӧ�ɹ��ı�ʶ
        System.out.println(num);
        if(brand!=null&&num!=0)resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

