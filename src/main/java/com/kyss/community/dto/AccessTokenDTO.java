package com.kyss.community.dto;

import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

/**
 * @ClassName AccessTokenDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 11:08 AM
 * @Version 1.0
 **/

@Data
@JSONType(naming = PropertyNamingStrategy.SnakeCase)
public class AccessTokenDTO {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUrl;
    private String state;
}
