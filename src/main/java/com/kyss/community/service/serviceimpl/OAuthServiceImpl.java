package com.kyss.community.service.serviceimpl;

import com.kyss.community.generator.dao.UserMapper;
import com.kyss.community.generator.model.User;
import com.kyss.community.generator.model.UserExample;
import com.kyss.community.service.IOAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName OAuthServiceImpl
 * @Description TODO
 * @Author davidt
 * @Date 6/15/2020 2:46 PM
 * @Version 1.0
 **/

@Service
public class OAuthServiceImpl implements IOAuthService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer insertOrUpdate(User user) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> findUser = userMapper.selectByExample(example);
        Integer col = 0;
        if (findUser.size() == 0) {
            user.setGmtCreate(System.currentTimeMillis());
            col = userMapper.insert(user);
        } else {
            user.setId(findUser.get(0).getId());
            user.setGmtModified(System.currentTimeMillis());
            col = userMapper.updateByPrimaryKey(user);
        }
        return col;
    }
}
