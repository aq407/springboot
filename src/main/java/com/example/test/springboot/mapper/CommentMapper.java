package com.example.test.springboot.mapper;
import com.example.test.springboot.moder.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CommentMapper {
    @Insert("insert into comment(parent_id,commentator,gmt_create,content,question_title,name,ico)values(#{parentId},#{commentator},#{gmt_create},#{content},#{questionTitle},#{name},#{ico})")
    void reply(Comment comment);

    @Select("select * from comment")
    List<Comment> getReply();

}
