package com.kyss.community.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kyss.community.dto.AccessTokenDTO;
import com.kyss.community.dto.GitHubUserDTO;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName GitHubProvider
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 11:06 AM
 * @Version 1.0
 **/

@Component
public class GitHubProvider {

    public static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    public static final String logUrl = "https://github.com/login/oauth/access_token";
    public static final String userUrl = "https://api.github.com/user";


    OkHttpClient client = new OkHttpClient();

    public String getAccessToken(AccessTokenDTO accessTokenDTO) throws IOException {

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .url(logUrl)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String token = response.body().string();
            System.out.println(token);
            return token;
        }
    }

        public GitHubUserDTO getUserInfo(String token) throws IOException {
        JSONObject jsonObject = JSON.parseObject(token);
        String stringToken = (String) jsonObject.get("access_token");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .header("Authorization", "token " + stringToken)
                .url(userUrl)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String userInfo = response.body().string();
            System.out.println(userInfo);
            JSONObject userInfoObj = JSON.parseObject(userInfo);
            GitHubUserDTO gitHubUserDTO = new GitHubUserDTO();
            gitHubUserDTO.setId(((Number) userInfoObj.get("id")).longValue());
            gitHubUserDTO.setName((String) userInfoObj.get("name"));
            gitHubUserDTO.setBio((String) userInfoObj.get("bio"));
            gitHubUserDTO.setAvatarUrl((String) userInfoObj.get("avatar_url"));
            return gitHubUserDTO;
        }
    }
}
