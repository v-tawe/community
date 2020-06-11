package com.kyss.community.controller;

import com.kyss.community.dto.AccessTokenDTO;
import com.kyss.community.dto.GitHubUserDTO;
import com.kyss.community.modle.User;
import com.kyss.community.provider.GitHubProvider;
import com.kyss.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName OAuthController
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 11:01 AM
 * @Version 1.0
 **/

@Controller
public class OAuthController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId(clientId);
        accessTokenDTO.setClientSecret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUrl(redirectUri);
        accessTokenDTO.setState(state);
        try {
            String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
            GitHubUserDTO gitHubUserDTO = gitHubProvider.getUserInfo(accessToken);
            System.out.println(gitHubUserDTO.getName());
            if (gitHubUserDTO != null) {
                request.getSession().setAttribute("user", gitHubUserDTO);

                // save to h2 database
                String token = UUID.randomUUID().toString();
                User user = new User();
                user.setName(gitHubUserDTO.getName());
                user.setToken(token);
                user.setAccountId(String.valueOf(gitHubUserDTO.getId()));
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setAvatarUrl(gitHubUserDTO.getAvatarUrl());
                userMapper.insert(user);

                // add cookie
                response.addCookie(new Cookie("token", token));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
