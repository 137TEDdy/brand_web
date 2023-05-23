package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User select(@Param("username") String username, @Param("password")  String password);

    @Select("select count(*) from t_user where username=#{username}")
    int selectIfExist(String username);
    @Insert("insert into t_user values(null,#{username},#{password})")
    int register(@Param("username") String username, @Param("password")  String password);

}
