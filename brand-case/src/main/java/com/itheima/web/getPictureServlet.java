package com.itheima.web;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/getPictureServlet")
public class getPictureServlet extends HttpServlet {
    BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端数据 [curPage,pageSize] 通过get请求：”url?curPage=4&pageSize=5“
//        String _curPage=req.getParameter("currentPage");
//        String _pageSize=req.getParameter("pageSize");
//        String _num=req.getParameter("num");
//        int curPage=Integer.valueOf(_curPage);
//        int pageSize=Integer.parseInt(_pageSize);
//        int num=Integer.parseInt(_num);
        String path=req.getParameter("path");
        System.out.println("path= "+path);
        System.out.println("okk");
        //byte[] bytes=brandService.selectPicture(curPage,pageSize,num);
        byte[] bytes=brandService.selectPicture(1,5,2);
        //for(int i=0;i<pageBean.getRows().size();i++)System.out.println(pageBean.getRows().get(i).getBlob());
        //4. 写数据
        //resp.setContentType("text/json;charset=utf-8"); //告知浏览器响应的数据是什么， 告知浏览器使用什么字符集进行解码
        //resp.getWriter().write(jsonString);
        //resp.setContentType("image/jpeg");
        //BufferedImage bi= ImageIO.read(f);
        //resp.setContentType("image/jpg");
        OutputStream out=resp.getOutputStream();
        //ImageIO.write(bi, "png", os);	}
        out.write(bytes);
        out.flush();

}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}