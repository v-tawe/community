package com.kyss.community.mapper;

import com.github.pagehelper.Page;
import com.kyss.community.modle.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ClassName QuestionMapper
 * @Description TODO
 * @Author davidt
 * @Date 6/5/2020 4:09 PM
 * @Version 1.0
 **/

@Mapper
public interface QuestionMapper {

    /**
     * create a new question
     */
    @Insert("INSERT INTO question(title, description, gmt_create, gmt_modified, creator, tag) " +
            "VALUES(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);

    @Select("SELECT * FROM question")
    public Page<Question> selectAll();

    @Select("SELECT * FROM question WHERE creator=#{userId}")
    public Page<Question> selectAllById(Long userId);

    @Select("SELECT * FROM question WHERE id=#{questionId}")
    Question selectById(Long questionId);
}
