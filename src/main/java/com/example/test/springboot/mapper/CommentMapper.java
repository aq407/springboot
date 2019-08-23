package com.example.test.springboot.mapper;
import com.example.test.springboot.dto.CommentDTO;
import com.example.test.springboot.moder.Comment;
import com.example.test.springboot.moder.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    //添加回复
    @Insert("insert into comment(parent_id,commentator,gmt_create,content,question_title,name)values(#{parentId},#{commentator},#{gmt_create},#{content},#{questionTitle},#{name})")
    void reply(Comment comment);

    @Select("select * from comment")//显示回复列表
    List<Comment> getReply();

    @Select("select * from t_user where id = #{id}")
    List<User> getIco(Integer creator);


}
