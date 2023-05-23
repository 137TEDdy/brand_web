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
        //1. ��ȡ�û���������
        String username = req.getParameter("username");
        String password =req.getParameter("password");
        String remember = req.getParameter("remember");

        System.out.println(username+" "+password+" "+remember);
        //2. ����service��ѯ
        User user = userService.select(username, password);
        System.out.println(user);
        PrintWriter writer = resp.getWriter();

        //3. �ж�
        if(user != null){
            //��¼�ɹ�����ת����ѯ���е�BrandServlet
//            if("1".equals(remember)){   //1дǰ�棬��ֹremember��ָ���쳣
//                //1. ����Cookie����
//                Cookie c_username = new Cookie("username",username);
//                Cookie c_password = new Cookie("password",password);
//                // ����Cookie�Ĵ��ʱ��
//                c_username.setMaxAge( 60 * 60 * 24 * 7);
//                c_password.setMaxAge( 60 * 60 * 24 * 7);
//                //2. ����
//                resp.addCookie(c_username);
//                resp.addCookie(c_password);
//            }
            //����½�ɹ����user���󣬴洢��session. Ŀ�ģ��������ݣ� ��request�Ĺ������������ת�����ҹرպ����ʧ��
            //request��cookie��session���ܴ����ݣ�������request���һ����һ���Ե�����(�����û���������֤��������ʾ��Ϣ������Ҫһֱ��ʾ)���������requestת��ʹ��
            //HttpSession session = req.getSession();
            //session.setAttribute("user",user);
            //resp.sendRedirect("brand.html");  //����selectALL
            resp.getWriter().write("success");

            //String contextPath = req.getContextPath();
            //�ض���
            //�й������ݾ�������ת������֮�ض����ض������ܸ��ߣ���ַ����仯�����������󣿣��������ֶ�����

        }else {
            // ��¼ʧ��,
            // �洢������Ϣ��request
            //req.setAttribute("login_msg","�û������������");
            // ��ת��login.jsp
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
