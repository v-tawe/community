package com.kyss.community.service;

import com.kyss.community.generator.model.User;

/**
 * @ClassName OAuthService
 * @Description TODO
 * @Author davidt
 * @Date 6/15/2020 2:46 PM
 * @Version 1.0
 **/
public interface IOAuthService {

    Integer insertOrUpdate(User user);
}
