package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    public User select(String username, String password){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper= sqlSession.getMapper(UserMapper.class);
        User user=mapper.select(username,password);
        sqlSession.close();

        return user;
    }

    public int addUser(String username,String password){
        SqlSession sqlSession=factory.openSession();
        UserMapper mapper= sqlSession.getMapper(UserMapper.class);
        if(mapper.selectIfExist(username)>0)return -1;
        int res=mapper.register(username,password);
        //System.out.println(username+" "+password);
        sqlSession.commit();
        sqlSession.close();
        return res;

    }
}
