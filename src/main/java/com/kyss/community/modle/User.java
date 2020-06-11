package com.kyss.community.modle;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 4:28 PM
 * @Version 1.0
 **/

@Data
public class User {
    private Long id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
