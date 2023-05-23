package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet  extends HttpServlet {
    UserService userService=new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取用户名和密码
        String username = req.getParameter("username");
        String password =req.getParameter("password");
        String remember = req.getParameter("remember");

        System.out.println(username+" "+password+" "+remember);
        //2. 调用service查询
        User user = userService.select(username, password);
        System.out.println(user);
        PrintWriter writer = resp.getWriter();

        //3. 判断
        if(user != null){
            //登录成功，跳转到查询所有的BrandServlet
//            if("1".equals(remember)){   //1写前面，防止remember空指针异常
//                //1. 创建Cookie对象
//                Cookie c_username = new Cookie("username",username);
//                Cookie c_password = new Cookie("password",password);
//                // 设置Cookie的存活时间
//                c_username.setMaxAge( 60 * 60 * 24 * 7);
//                c_password.setMaxAge( 60 * 60 * 24 * 7);
//                //2. 发送
//                resp.addCookie(c_username);
//                resp.addCookie(c_password);
//            }
            //将登陆成功后的user对象，存储到session. 目的：共享数据； （request的共享仅限于请求转发，且关闭后会消失）
            //request、cookie、session都能存数据，区别是request存的一般是一次性的数据(比如用户名密码验证码错误的提示信息，不需要一直显示)，所以配合request转发使用
            //HttpSession session = req.getSession();
            //session.setAttribute("user",user);
            //resp.sendRedirect("brand.html");  //跳到selectALL
            resp.getWriter().write("success");

            //String contextPath = req.getContextPath();
            //重定向
            //有共享数据就用请求转发，反之重定向，重定向性能更高，地址栏会变化，更符合需求？；这里两种都可以

        }else {
            // 登录失败,
            // 存储错误信息到request
            //req.setAttribute("login_msg","用户名或密码错误");
            // 跳转到login.jsp
            writer.write("wrong");
            //req.getRequestDispatcher("/login.html").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        this.doGet(req, resp);
    }
}
