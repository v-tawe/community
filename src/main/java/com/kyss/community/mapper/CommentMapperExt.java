package com.kyss.community.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName CommentMapperExt
 * @Description TODO
 * @Author davidt
 * @Date 6/18/2020 5:58 PM
 * @Version 1.0
 **/

@Mapper
public interface CommentMapperExt {
    @Update("UPDATE comment SET comment_count=comment_count + 1 WHERE id=#{commentId}")
    Integer incCommentCount(Long commentId);
}
