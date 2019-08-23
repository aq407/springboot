package com.example.test.springboot.mapper;

import com.example.test.springboot.moder.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into t_question(title,description,tag,creator,Creation_time)values(#{title},#{description},#{tag},#{creator},#{CreationTime})")
    void create(Question question);

    @Select("select * from t_question order by id desc limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from t_question")
    Integer count();

    @Select("select * from t_question where creator = #{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") int userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("select count(1) from t_question where creator = #{userId}")
    Integer countByUserId(@Param("userId") int userId);

    @Select("select * from t_question where id = #{id}")
    Question getById(@Param("id")Integer id);

    @Update("UPDATE t_question SET title = #{title}, description = #{description},tag = #{tag} WHERE id=#{id}")
    void update(Question question);

}
