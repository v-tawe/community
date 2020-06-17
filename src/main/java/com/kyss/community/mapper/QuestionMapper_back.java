package com.kyss.community.mapper;

import com.github.pagehelper.Page;
import com.kyss.community.dto.QuestionDTO;
import com.kyss.community.generator.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName QuestionMapper
 * @Description TODO
 * @Author davidt
 * @Date 6/5/2020 4:09 PM
 * @Version 1.0
 **/

@Mapper
public interface QuestionMapper_back {

    /**
     * create a new question
     */
    @Insert("INSERT INTO question(title, description, gmt_create, gmt_modified, creator, tag) " +
            "VALUES(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    Integer create(Question question);

    @Select("SELECT * FROM question")
    Page<Question> selectAll();

    @Select("SELECT * FROM question WHERE creator=#{userId}")
    Page<Question> selectAllById(Long userId);

    @Select("SELECT * FROM question WHERE id=#{questionId}")
    Question selectById(Long questionId);

    @Update("UPDATE question SET title=#{title}, description=#{description}, gmt_modified=#{gmtModified}, " +
            "creator=#{creator}, tag=#{tag} WHERE id=#{id}")
    Integer update(QuestionDTO questionDTO);
}
