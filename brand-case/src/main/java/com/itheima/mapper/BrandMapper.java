package com.itheima.mapper;

import com.itheima.pojo.Brand;
import com.mysql.cj.jdbc.Blob;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BrandMapper {
    @Select("select * from tb_brand")
    List<Brand> selectAll();

    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    int add(Brand brand);


    void deleteByIds(@Param("ids") int []ids);

    @Select("select * from tb_brand limit #{begin},#{size}")
    List<Brand> selectByPage(@Param("begin") int curPage,@Param("size")int PageSize);

    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},"
            +"status=#{status} where id=#{id}")
    void update(Brand brand);

    //修改品牌
    @Select("select * from tb_brand where brand_name=#{brandName} and company_name=#{companyName}")
    List<Brand> query(@Param("brandName") String brandName,@Param("companyName")String companyName);

    //支持分页的修改品牌
    List<Brand> selectByPageAndCondition(@Param("begin") int curPage,@Param("size")int PageSize,@Param("brand") Brand brand);
    int selectTotalCountByCondition(Brand brand);

    @Select("select picture from tb_brand where id=#{num}")
    byte[] selectPicture(int num);

    @Select("select * from brand_offshelf  limit #{begin},#{size} ")
    List<Brand> selectByPageAndStatus(@Param("begin") int curPage,@Param("size")int PageSize);
    @Select("select count(*) from brand_offshelf")
    int selectTotalCountWithStatus();

}
