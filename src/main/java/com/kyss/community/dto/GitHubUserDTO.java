package com.kyss.community.dto;

import lombok.Data;

/**
 * @ClassName GitHubUserDTO
 * @Description TODO
 * @Author davidt
 * @Date 6/4/2020 12:09 PM
 * @Version 1.0
 **/

@Data
public class GitHubUserDTO {
    private Long id;
    private String name;
    private String bio;
    private String avatarUrl;
}
