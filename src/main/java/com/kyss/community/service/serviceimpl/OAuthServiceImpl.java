package com.kyss.community.service.serviceimpl;

import com.kyss.community.mapper.UserMapper;
import com.kyss.community.modle.User;
import com.kyss.community.service.OAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName OAuthServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/15/2020 2:46 PM
 * @Version 1.0
 **/

@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer insertOrUpdate(User user) {
        User findUser = userMapper.findByAccountId(user.getAccountId());
        Integer col = 0;
        if (findUser == null) {
            user.setGmtCreate(System.currentTimeMillis());
            col = userMapper.insert(user);
        } else {
            BeanUtils.copyProperties(user, findUser);
            user.setGmtModified(System.currentTimeMillis());
            col = userMapper.update(findUser);
        }
        return col;
    }
}
