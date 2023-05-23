package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.util.SqlSessionFactoryUtils;

import com.mysql.cj.jdbc.Blob;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.*;
import java.util.List;

public class BrandService {
    SqlSessionFactory factory= SqlSessionFactoryUtils.getSqlSessionFactory();
    public List<Brand> selectAll(){
        SqlSession sqlSession=factory.openSession();
        BrandMapper mapper= sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands=mapper.selectAll();
        sqlSession.close();

        return brands;
    }
    public int add(Brand brand) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        int num=mapper.add(brand);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
        return num;
    }

    public void deleteByIds(int []ids){
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.deleteByIds(ids);
        sqlSession.commit();//提交事务

        //5. 释放资源
        sqlSession.close();
    }

    public PageBean<Brand> selectByPage(int curPage,int pageSize){
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(curPage-1)*pageSize;

        List<Brand> brands=mapper.selectByPage(begin,pageSize);
        int totalCount=mapper.selectTotalCount();
        sqlSession.close();
        //设置pageBean
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

    public void update(Brand brand){
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);
        sqlSession.commit();//提交事务

        sqlSession.close();
    }

    public List<Brand> query(String brandName,String companyName){
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        List<Brand> brands=mapper.query(brandName,companyName);
        sqlSession.close();

        return brands;
    }

    public PageBean<Brand> selectByPageAndCondition(int curPage,int pageSize,Brand brand){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(curPage-1)*pageSize;
        //处理模糊查询
        //不能写到mapper，查询语句会变成 like %'华为'%，百分号写不进去
        String brandName=brand.getBrandName();
        if(brandName!=null&&brandName.length()>0){
            brandName='%'+brandName+'%';
            brand.setBrandName(brandName);
        }
        String companyName=brand.getCompanyName();
        if(companyName!=null&&companyName.length()>0){
            companyName='%'+companyName+'%';
            brand.setCompanyName(companyName);
        }

        List<Brand> brands=mapper.selectByPageAndCondition(begin,pageSize,brand);
        int count=mapper.selectTotalCountByCondition(brand);
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(count);
        //测试blob
//        Brand brand1 = mapper.selectPicture();
//        try {
//            OutputStream out = new FileOutputStream(new File("D:\\1\\blobTest.png"));
//            out.write(brand1.getBlob());
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //System.out.println(brand1.getBlob());
        return pageBean;
    }

    public byte[] selectPicture(int curPage,int pageSize,int num){
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(curPage-1)*pageSize;
        byte[] bytes = mapper.selectPicture(begin + num);
        sqlSession.close();
        return bytes;
    }

    public PageBean<Brand> selectByPageAndStatus (int curPage,int pageSize){
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        int begin=(curPage-1)*pageSize;

        List<Brand> brands=mapper.selectByPageAndStatus(begin,pageSize);
        int totalCount=mapper.selectTotalCountWithStatus();
        sqlSession.close();
        //设置pageBean
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        return pageBean;
    }

}
