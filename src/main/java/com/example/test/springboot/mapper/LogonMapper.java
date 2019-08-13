package com.example.test.springboot.mapper;


import com.example.test.springboot.moder.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogonMapper {
    @Insert("insert into t_user(username,password,iconurl,Creation_time)values(#{username},#{password},#{iconurl},#{CreationTime})")
    void create(User user);

    @Select("select * from t_user;")
    List<User> findAll();

    @Select("select * from t_user WHERE username = #{username} and password = #{password}")
    User findUser(String username, String password);

    @Update("UPDATE t_user SET username = #{uname}, password = #{upwd} WHERE id=#{uid}")
    void update(String uname, String upwd, Integer uid);

    @Delete("Delete from t_user where id = #{id}")
    void delete(int id);


    @Select("select * from t_user where id = #{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from t_user where username = #{username}")
    User getId(@Param("username") String username);


}
