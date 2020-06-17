package com.kyss.community.mapper;

import com.kyss.community.generator.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 4:27 PM
 * @Version 1.0
 **/

public interface UserMapperExt {

    /**
     * insert a new user
     *
     * @param user
     */
    @Insert("INSERT INTO user(name, account_id, token, gmt_create, gmt_modified, avatar_url) " +
            "VALUES(#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    Integer insert(User user);

    /**
     * used for verify log status
     *
     * @param token
     * @return
     */
    @Select("SELECT * FROM user WHERE token=#{token}")
    User findByToken(String token);

    /**
     * select user by Id
     *
     * @param Id
     * @return
     */
    @Select("SELECT * FROM user WHERE id=#{id}")
    User findById(Long Id);

    /**
     * select user form accountId
     *
     * @param accountId
     * @return
     */
    @Select("SELECT * FROM user WHERE account_id=#{accountId}")
    User findByAccountId(String accountId);

    @Update("UPDATE user SET account_id=#{accountId}, name=#{name}, token=#{token}, " +
            "gmt_modified=#{gmtModified}, bio=#{bio}, avatar_url=#{avatarUrl} WHERE id=#{id}")
    Integer update(User findUser);
}
