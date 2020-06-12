package com.kyss.community.service;

import com.kyss.community.modle.User;

/**
 * @ClassName OAuthService
 * @Description TODO
 * @Author davidt
 * @Date 6/15/2020 2:46 PM
 * @Version 1.0
 **/
public interface OAuthService {

    Integer insertOrUpdate(User user);
}
