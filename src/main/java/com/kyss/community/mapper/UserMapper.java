package com.kyss.community.mapper;

import com.kyss.community.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 4:27 PM
 * @Version 1.0
 **/

@Mapper
public interface UserMapper {

    /**
     * insert a new user
     * @param user
     */
    @Insert("INSERT INTO user(name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "VALUES(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    public void insert(User user);

    /**
     * used for verify log status
     * @param token
     * @return
     */
    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(String token);

    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Long Id);
}
